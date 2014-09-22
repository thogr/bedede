package com.github.thogr.bedede.state;

public final class Otherwise {
    private final String message;

    private Otherwise(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
    public static Otherwise otherwise(String message) {
    	return new Otherwise(message);
    }
}