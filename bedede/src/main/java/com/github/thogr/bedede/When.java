package com.github.thogr.bedede;

public class When {

    private final BehaviorController controller;

    When(final BehaviorController controller) {
        this.controller = controller;
    }

    public final <T> Then<T> then(final Class<T> state) {
        return controller.then(state);
    }
}
