package com.github.thogr.bedede.conditions;

import com.github.thogr.bedede.core.internal.Verifiable;

public abstract class Expecting<T> extends Verifiable<T> {

    /**
     * Combine two "expecting" conditions
     * @param other the other condition
     * @return the resulting condition
     */
    public abstract Expecting<T> and(Expecting<T> other);

}
