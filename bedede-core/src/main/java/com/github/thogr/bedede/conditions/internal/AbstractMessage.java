package com.github.thogr.bedede.conditions.internal;

public abstract class AbstractMessage {

    private final String message;

    protected AbstractMessage(final String message) {
        this.message = message;
    }

    protected String getMessage() {
        return message;
    }

}
