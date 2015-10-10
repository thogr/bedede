package com.github.thogr.bedede.core.internal;

public abstract class Wrapped<T> {

    private final T obj;

    protected Wrapped(final T obj) {
        this.obj = obj;
    }

    T getWrapped() {
        return obj;
    }

    protected static <T> T getWrapped(final Wrapped<T> wrapped) {
        return wrapped.getWrapped();
    }
}
