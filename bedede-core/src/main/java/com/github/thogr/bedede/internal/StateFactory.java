package com.github.thogr.bedede.internal;

public interface StateFactory {
    <T> T createState(Class<T> state);
}
