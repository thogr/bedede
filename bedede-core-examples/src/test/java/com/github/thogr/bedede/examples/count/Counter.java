package com.github.thogr.bedede.examples.count;

public class Counter {

    private int seconds;

    public boolean isStopped() {
        return seconds == 0;
    }

    /**
     * Starts the counter
     * @param secs initial value
     */
    public void start(final int secs) {
        this.seconds = secs;
    }

    /**
     * Decrease the value
     */
    public void decrease() {
        this.seconds--;
    }
}
