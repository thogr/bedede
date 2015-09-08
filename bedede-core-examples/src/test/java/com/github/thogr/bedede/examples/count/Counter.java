package com.github.thogr.bedede.examples.count;

public class Counter {

    private int seconds;

    public boolean isStopped() {
        return seconds == 0;
    }

    public void start(final int secs) {
        this.seconds = secs;
    }

    public void decrease() {
        this.seconds--;
    }
}
