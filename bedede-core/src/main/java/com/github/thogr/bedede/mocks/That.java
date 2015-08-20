package com.github.thogr.bedede.mocks;

public class That<T> {

    private T stubbing;

    public That(T stubbing) {
        this.stubbing = stubbing;
    }

    public T getStubbing() {
        return stubbing;
    }
}
