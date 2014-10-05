package com.github.thogr.bedede;

public final class When<S> {

    private final BehaviorController controller;
    private final Class<S> state;

    When(final Class<S> state, final BehaviorController controller) {
        this.state = state;
        this.controller = controller;
    }

    public Then<S> then() {
        return then(state);
    }

    public <T> Then<T> then(final Class<T> state) {
        return controller.then(state);
    }
}
