package com.github.thogr.bedede.state.internal;

import java.util.Map;

public final class DefaultInitialStateFactory implements InitialStateFactory {

    @Override
    public <T> T createInitialState(
            final StateFactory factory,
            final Class<T> stateClass,
            final Map<String, String> params) {

        return factory.createState(stateClass);
    }

}
