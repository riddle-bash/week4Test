package com.example.dictionary.processData;

public class NotFoundWordException extends Exception{
    public NotFoundWordException(String str) {
        super(str);
    }
}
