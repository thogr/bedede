package com.github.thogr.bedede.core.internal;

public interface StateFactory {
    <T> T createState(Class<T> state);
}
