package com.github.thogr.bedede;

import java.util.function.BiFunction;

import com.github.thogr.bedede.core.internal.AbstractBiTransformer;

public abstract class BiTransforming<T1, T2, S> extends AbstractBiTransformer<T1, T2, S> {

    protected BiTransforming(final BiFunction<T1, T2, S> function) {
        super(function);
    }
}
