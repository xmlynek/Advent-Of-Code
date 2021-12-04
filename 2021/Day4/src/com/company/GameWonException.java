package com.company;

public class GameWonException extends Exception{
    public GameWonException() {
    }

    public GameWonException(String message) {
        super(message);
    }
}
