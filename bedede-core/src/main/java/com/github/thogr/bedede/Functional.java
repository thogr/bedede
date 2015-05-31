package com.github.thogr.bedede;

import java.util.function.Function;

public final class Functional<T, S> {
    Function<T,S> function;

    public Functional(Function<T, S> function) {
        this.function = function;
    }
}
