package com.github.thogr.bedede.examples.countdown;

public class Counter {

    private int seconds;

    public boolean isStopped() {
        return seconds == 0;
    }

    public void start(int seconds) {
        this.seconds = seconds;
    }

    public void decrease() {
        this.seconds--;
    }
}
