package com.github.thogr.bedede;


public final class Assuming<T> extends AbstractAssuming<T> {

    Assuming(final Class<T> state, final BehaviorController controller) {
        super(state, controller);
    }

    public When<T> when(final ActionExpression<T> action) {
        return getController().when(action, state);
    }
}
