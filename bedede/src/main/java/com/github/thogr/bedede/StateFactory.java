package com.github.thogr.bedede;

public interface StateFactory {
    <T> T createState(Class<T> state);
}
