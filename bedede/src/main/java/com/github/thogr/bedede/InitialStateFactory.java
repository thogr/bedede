package com.github.thogr.bedede;

import java.util.Map;

public interface InitialStateFactory {

    <T> T createInitialState(
            final StateFactory factory, Class<T> stateClass, Map<String, String> params);

}
