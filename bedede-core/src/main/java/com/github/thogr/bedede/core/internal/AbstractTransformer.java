package com.github.thogr.bedede.core.internal;

import java.util.function.Function;

public abstract class AbstractTransformer<T, S> {

    private final Function<T, S> function;

    protected AbstractTransformer(final Function<T, S> function) {
        this.function = function;
    }

    Function<T, S> getFunction() {
        return function;
    }

}
