package com.github.thogr.bedede.core.internal;

import java.util.Map;

public interface InitialStateFactory {

    @Internal
    <T> T createInitialState(
            final StateFactory factory, Class<T> stateClass, Map<String, String> params);

}
