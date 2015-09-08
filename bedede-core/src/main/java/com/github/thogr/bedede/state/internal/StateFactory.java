package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.core.internal.Internal;

public interface StateFactory {

    @Internal
    <T> T createState(Class<T> state);
}
