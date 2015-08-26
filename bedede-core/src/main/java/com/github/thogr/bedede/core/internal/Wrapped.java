package com.github.thogr.bedede.core.internal;

public abstract class Wrapped<T> {

    private T obj;

    protected Wrapped(T obj) {
        this.obj = obj;
    }

    T getWrapped() {
        return obj;
    }

    static <T> T getWrapped(Wrapped<T> wrapped) {
        return wrapped.getWrapped();
    }
}
