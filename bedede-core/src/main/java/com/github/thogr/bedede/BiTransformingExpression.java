package com.github.thogr.bedede;

import java.util.function.BiFunction;

public class BiTransformingExpression<T1, T2, S> {
    BiFunction<T1, T2, S> function;

    public BiTransformingExpression(BiFunction<T1, T2, S> function) {
        this.function = function;
    }
}
