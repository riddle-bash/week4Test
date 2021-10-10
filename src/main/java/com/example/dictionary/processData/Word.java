package com.example.dictionary.processData;

public class Word {
    public String face_word;
    public String pronunciation;
    public String[] types;
    public String[][] meanings;

    public Word() {
        super();
        face_word = null;
        pronunciation = null;
        types = new String[3];
        meanings = new String[3][3];

        for (int i = 0; i < 3; i++) {
            types[i] = null;
        }
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                meanings[i][j] = null;
            }
        }
    }
}
