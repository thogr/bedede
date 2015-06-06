package com.github.thogr.bedede;

import java.util.function.Function;

public final class TransformingExpression<T, S> {
    Function<T,S> function;

    public TransformingExpression(Function<T, S> function) {
        this.function = function;
    }
}
