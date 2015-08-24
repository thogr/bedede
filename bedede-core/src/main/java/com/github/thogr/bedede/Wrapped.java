package com.github.thogr.bedede;

public abstract class Wrapped<T> {

    private T wrapped;

    protected Wrapped(T wrapped) {
        this.wrapped = wrapped;
    }

    protected T getWrapped() {
        return wrapped;
    }

}
