package com.github.thogr.bedede;

import java.util.function.Function;

import com.github.thogr.bedede.core.internal.AbstractTransformer;

public abstract class Transforming<T, S> extends AbstractTransformer<T, S> {

    protected Transforming(Function<T, S> function) {
        super(function);
    }

}
