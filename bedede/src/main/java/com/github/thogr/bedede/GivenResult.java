package com.github.thogr.bedede;

public final class GivenResult<T> {

    private final BehaviorController controller;
    private final Class<T> state;

    GivenResult(final BehaviorController controller, final Class<T> state) {
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
}
