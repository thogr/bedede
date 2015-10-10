package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.core.internal.Wrapped;

public abstract class WrappedState<T> extends Wrapped<Class<T>> {

    protected WrappedState(final Class<T> state) {
        super(state);
    }

    Class<T> getState() {
        return Wrapped.getWrapped(this);
    }
}
