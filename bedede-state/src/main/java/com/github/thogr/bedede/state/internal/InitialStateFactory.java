package com.github.thogr.bedede.state.internal;

import java.util.Map;

import com.github.thogr.bedede.core.internal.Internal;

public interface InitialStateFactory {

    @Internal
    <T> T createInitialState(
            final StateFactory factory, Class<T> stateClass, Map<String, String> params);

}
