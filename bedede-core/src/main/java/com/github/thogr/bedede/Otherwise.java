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

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Otherwise other = (Otherwise) obj;
        if (message == null) {
            if (other.message != null)
                return false;
        } else if (!message.equals(other.message))
            return false;
        return true;
    }
}
