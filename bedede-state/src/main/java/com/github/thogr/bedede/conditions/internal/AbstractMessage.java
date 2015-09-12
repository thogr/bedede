package com.github.thogr.bedede.conditions.internal;

public abstract class AbstractMessage {

    private final String message;

    protected AbstractMessage(final String message) {
        this.message = message;
    }

    // CHECKSTYLE:OFF InlineConditional
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((message == null) ? 0 : message.hashCode());
        return result;
    }
    // CHECKSTYLE:ON

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AbstractMessage other = (AbstractMessage) obj;
        if (message == null) {
            if (other.message != null) {
                return false;
            }
        } else if (!message.equals(other.message)) {
            return false;
        }
        return true;
    }

    protected String getMessage() {
        return message;
    }
}
