package com.github.thogr.bedede.state;

import com.github.thogr.bedede.core.internal.Internal;
import com.github.thogr.bedede.state.internal.WrappedState;

/**
 * A reference to a state defined by a class
 * @param <T> the type of the class that defines the state
 */
public class AtState<T> extends WrappedState<T> {

    private AtState(final Class<T> state) {
        super(state);
    }

    @Internal
    public static <T> AtState<T> at(final Class<T> state) {
        return new AtState<T>(state);
    }
}
