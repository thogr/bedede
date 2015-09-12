package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.state.TargetWhen;
import com.github.thogr.bedede.state.ThenExpecting;

public class TargetWhenImpl<T, S> extends AbstractWhenImpl<S> implements TargetWhen<T, S> {

    TargetWhenImpl(final Class<T> target, final AbstractStateControlled<S> delegate) {
        super(delegate.getState(), delegate.getController());
    }

    @Override
    public ThenExpecting<T> then(final Class<T> target) {
        return thenState(target);
    }
}
