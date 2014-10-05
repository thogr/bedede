package com.github.thogr.bedede;

public final class Assuming<T> {

    private final BehaviorController controller;
    private final Class<T> state;

    Assuming(final Class<T> state, final BehaviorController controller) {
        this.controller = controller;
        this.state = state;
    }
/*
    public void when(final Action<? super T> action) {
        controller.when(action);
    }
*/
    public When when(final ActionExpression<T> action) {
        return controller.when(action, state);
    }

    public Then<T> then() {
        return controller.then(state);
    }
}
