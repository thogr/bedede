package com.github.thogr.bedede.conditions.internal;

public class AbstractMessage {

    protected final String message;

    protected AbstractMessage(final String message) {
        this.message = message;
    }

    protected String getMessage() {
        return message;
    }

}
