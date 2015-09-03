package com.github.thogr.bedede.core.internal;

import java.util.function.Function;

import com.github.thogr.bedede.Transforming;

public final class TransformingImpl<T, S> extends Transforming<T, S> {

    TransformingImpl(final Function<T, S> function) {
        super(function);
    }
}
