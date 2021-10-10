package com.example.dictionary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Objects;

import com.example.dictionary.processData.NotFoundWordException;
import com.example.dictionary.processData.Word;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("main-view.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("styles/style.css")).toExternalForm());
    stage.setTitle("Dictionary");
    stage.setScene(scene);
    stage.show();
  }

  public static Word getData(String w) {
    Word word = new Word();

    //call data from database
    String url = "jdbc:mysql://localhost:3306/dictionary";
    String username = "root";
    String password = "sondinh5310";

    try {
      //connection to database from local
      Connection connection = DriverManager.getConnection(url, username, password);

      //statement in mysql
      String sql = "SELECT * from data WHERE data.word = '" + w + " '";
      Statement statement = connection.createStatement();

      //call data and store it in result
      statement.executeQuery(sql);
      ResultSet result =statement.executeQuery(sql);

      int index_type = 0;
      int index_meaning = 0;

      if (result.next() == false) {
        throw new NotFoundWordException("Not found world");
      }
      //only get face_word and pronunciation one time
      word.face_word = result.getString("word");
      word.pronunciation = result.getString("pronunciation");
      //get types max 3 time and meaning max 9 time
      word.types[index_type] = result.getString("type");
      word.meanings[index_type][index_meaning] = result.getString("meaning");

      while (result.next()) {
        String type = result.getString("type");
        String meaning = result.getString("meaning");

        if (!type.equals(word.types[index_type])) {
          index_type ++;
          word.types[index_type] = type;
          index_meaning = 0;
        } else {
          index_meaning ++;
        }
        word.meanings[index_type][index_meaning] = meaning;
      }

      statement.close();
      connection.close();
    } catch (Exception e) {
      System.out.println(e);
      word = null;
      e.printStackTrace();
    }
    return word;
  }
  public static void printWord(Word word) {
    if (word == null) {
      return;
    }
    Word temp = word;
    System.out.printf(temp.face_word + "\n" + temp.pronunciation + "\n");
    for (int j = 0; j < 3; j++) {
      if (temp.types[j] != null) {
        System.out.printf(temp.types[j] + "\n");
        for (int k = 0; k < 3; k++) {
          if (temp.meanings[j][k] != null) {
            System.out.printf(temp.meanings[j][k] + "\n");
          } else {
            break;
          }
        }
      } else {
        break;
      }
    }
  }
  public static void main(String[] args) {
    launch();
    Word var = getData("ahaf");
    //print
    printWord(var);
  }

}