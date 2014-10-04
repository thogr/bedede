package com.github.thogr.bedede;

public final class Otherwise {
    private final String message;

    private Otherwise(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static Otherwise otherwise(final String message) {
        return new Otherwise(message);
    }
}
