package com.example.dictionary;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainController implements Initializable {

  ArrayList<String> words = new ArrayList<>(
          Arrays.asList("test", "dog","Human", "Days of our life", "The best day",
                  "Friends", "Animal", "Human", "Humans", "Bear", "Life",
                  "This is some text", "Words", "222", "Bird", "Dog", "A few words",
                  "Subscribe!", "SoftwareEngineeringStudent", "You got this!!",
                  "Super Human", "Super", "Like")
  );

  @FXML
  private TextField searchbar;

  @FXML
  private ListView<String> listview;

  @FXML
  void search() {
    listview.getItems().clear();
    listview.getItems().addAll(searchList(searchbar.getText(),words));
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    listview.getItems().addAll(words);
  }

  private List<String> searchList(String searchWords, List<String> listOfStrings) {

    List<String> searchWordsArray = Arrays.asList(searchWords.trim().split(" "));

    return listOfStrings.stream().filter(input -> {
      return searchWordsArray.stream().allMatch(word ->
              input.toLowerCase().contains(word.toLowerCase()));
    }).collect(Collectors.toList());
  }
}