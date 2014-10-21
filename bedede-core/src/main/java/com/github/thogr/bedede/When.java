package com.github.thogr.bedede;


public final class When<S> extends AbstractWhen<S> {

    When(final Class<S> state, final BehaviorController controller) {
        super(state, controller);
    }

    public <T> Then<T> then(final Class<T> target) {
        return thenState(target);
    }
}
