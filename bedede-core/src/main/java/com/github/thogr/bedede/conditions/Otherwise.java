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
}
