package com.github.thogr.bedede;

public final class DefaultInitialStateFactory implements InitialStateFactory {

    @Override
    public <T> T createInitialState(
            final StateFactory factory, final Class<T> stateClass, final Parameter... params) {
        return factory.createState(stateClass);
    }

}
