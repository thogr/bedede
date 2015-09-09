package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.state.TargetAssuming;
import com.github.thogr.bedede.state.TargetWhen;

public class TargetAssumingImpl<T, S> extends AbstractAssumedState<S>
implements TargetAssuming<T, S> {

    private final Class<T> target;
    private final AbstractAssumedState<S> delegate;

    TargetAssumingImpl(final Class<T> target, final AbstractAssumedState<S> delegate) {
        super(delegate.getState(), delegate.getController());
        this.target = target;
        this.delegate = delegate;
    }

    @Override
    public TargetWhen<T, S> when(final ActionExpression<S> action) {
        final StateBasedWhenImpl<S> whenResult =
                delegate.getController().when(action, delegate.getState());
        return new TargetWhenImpl<T, S>(target, whenResult);
    }
}
