package com.github.thogr.bedede.core.internal;

import java.util.function.BiFunction;

public class AbstractBiTransformer<T1, T2, S> {

    private BiFunction<T1, T2, S> function;

    protected AbstractBiTransformer(BiFunction<T1, T2, S> function) {
        this.function = function;
    }

    BiFunction<T1, T2, S> getFunction() {
        return function;
    }
}
