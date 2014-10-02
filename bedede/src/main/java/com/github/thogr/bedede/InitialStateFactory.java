package com.github.thogr.bedede;

public interface InitialStateFactory {

    <T> T createInitialState(final StateFactory factory, Class<T> stateClass, Parameter... params);

}
