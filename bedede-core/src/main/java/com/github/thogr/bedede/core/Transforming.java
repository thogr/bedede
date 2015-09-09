package com.github.thogr.bedede.core;

import java.util.function.Function;

import com.github.thogr.bedede.core.internal.AbstractTransformer;

public abstract class Transforming<T, S> extends AbstractTransformer<T, S> {

    protected Transforming(final Function<T, S> function) {
        super(function);
    }

}
