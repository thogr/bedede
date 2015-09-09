package com.github.thogr.bedede.conditions;

import com.github.thogr.bedede.conditions.internal.AbstractMessage;


public final class Otherwise extends AbstractMessage {

    private Otherwise(final String message) {
        super(message);
    }

    /**
     * Wraps a description of the unexpected
     * @see com.github.thogr.bedede.state.StateExpressions#expecting(Boolean, Otherwise)
     * @param message the text to be used as error message
     * @return the wrapped description
     */
    public static Otherwise otherwise(final String message) {
        return new Otherwise(message);
    }

    // CHECKSTYLE:OFF InlineConditionals
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getMessage() == null) ? 0 : getMessage().hashCode());
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
        final Otherwise other = (Otherwise) obj;
        if (getMessage() == null) {
            if (other.getMessage() != null) {
                return false;
            }
        } else if (!getMessage().equals(other.getMessage())) {
            return false;
        }
        return true;
    }
}
