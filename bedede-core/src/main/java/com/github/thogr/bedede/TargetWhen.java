package com.github.thogr.bedede;

public class TargetWhen<T, S> extends AbstractWhen<S> {

    TargetWhen(final Class<T> target, final When<S> delegate) {
        super(delegate.state, delegate.getController());
    }

    public ThenExpecting<T> then(final Class<T> target) {
        return thenState(target);
    }
}
