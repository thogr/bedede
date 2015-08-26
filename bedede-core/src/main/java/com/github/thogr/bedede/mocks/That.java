package com.github.thogr.bedede.mocks;

import com.github.thogr.bedede.core.internal.Wrapped;


public abstract class That<T> extends Wrapped<T> {

    protected That(T stubbing) {
        super(stubbing);
    }
}
