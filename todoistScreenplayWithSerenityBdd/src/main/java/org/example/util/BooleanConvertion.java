package org.example.util;

public enum BooleanConvertion {
    EXISTING(true),
    DO_NOT_EXIST(false);

    boolean value;

    BooleanConvertion(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }
}