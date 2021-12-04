package com.company;

public class Field {
    private final Integer value;
    private boolean guessed = false;

    public Field(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public boolean isGuessed() {
        return guessed;
    }

    public void setGuessed(boolean guessed) {
        this.guessed = guessed;
    }
}
