package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.StateBasedWhen;
import com.github.thogr.bedede.ThenExpecting;

public final class StateBasedWhenImpl<S> extends AbstractWhenImpl<S> implements StateBasedWhen<S> {

    StateBasedWhenImpl(final Class<S> state, final BehaviorController controller) {
        super(state, controller);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.When#then(java.lang.Class)
     */
    @Override
    public <T> ThenExpecting<T> then(final Class<T> target) {
        return thenState(target);
    }
}
