package com.github.thogr.bedede;

public class TargetAssuming<T, S> extends AbstractAssuming<S> {

    private final Class<T> target;

    TargetAssuming(final Class<T> target, final Assuming<S> delegate) {
        super(delegate.state, delegate.getController());
        this.target = target;
    }

    public TargetWhen<T, S> when(final ActionExpression<S> action) {
        return new TargetWhen<T, S>(target, getController().when(action, state));
    }

}
