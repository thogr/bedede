package com.github.thogr.bedede;

public final class GivenResult<T> {

    private final BehaviorController controller;

    GivenResult(final BehaviorController controller) {
        this.controller = controller;
    }

    public void when(final Action<? super T> action) {
        controller.when(action);
    }

    public void when(final ActionMethod<T> action) {
        controller.when(action);
    }

    public T when() {
        return controller.go(TypeArguments.typeArgument(this));
    }
}
