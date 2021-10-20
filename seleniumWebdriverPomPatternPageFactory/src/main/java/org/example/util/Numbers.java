package org.example.util;

public enum Numbers {
    ZERO(0),
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR(4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9),
    TEN(10),
    TWENTY(20),
    THIRTY(30);

    int value;

    Numbers(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
