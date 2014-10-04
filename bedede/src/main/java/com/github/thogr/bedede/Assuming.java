package com.github.thogr.bedede;

public final class Assuming<T> {

    private final BehaviorController controller;
    private final Class<T> state;

    Assuming(final Class<T> state, final BehaviorController controller) {
        this.controller = controller;
        this.state = state;
    }

    public void when(final Action<? super T> action) {
        controller.when(action);
    }

    public void when(final ActionMethod<T> action) {
        controller.when(action);
    }

    public T when() {
        return controller.go(state);
    }

    public Then<T> then() {
        return controller.then(state);
    }
}
