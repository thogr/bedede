package com.github.thogr.bedede.core.internal;

public interface StateFactory {

    @Internal
    <T> T createState(Class<T> state);
}
