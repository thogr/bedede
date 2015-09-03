package com.github.thogr.bedede.mocks;

import com.github.thogr.bedede.core.internal.Wrapped;


public abstract class Mocked<T> extends Wrapped<T> {

    protected Mocked(final T spec) {
        super(spec);
    }
}
