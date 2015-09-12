package com.github.thogr.bedede.examples;

public class Incrementable {
    private int value = 0;

    void increment() {
        incrementBy(1);
    }

    void incrementBy(final int i) {
        value += i;
    }

    int getValue() {
        return value;
    }
}
