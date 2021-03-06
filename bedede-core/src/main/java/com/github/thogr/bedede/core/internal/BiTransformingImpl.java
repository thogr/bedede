package com.github.thogr.bedede.core.internal;

import java.util.function.BiFunction;

import com.github.thogr.bedede.core.BiTransforming;

final class BiTransformingImpl<T1, T2, S> extends BiTransforming<T1, T2, S> {

    BiTransformingImpl(final BiFunction<T1, T2, S> function) {
        super(function);
    }
}
