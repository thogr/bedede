package com.github.thogr.bedede;

public class Then<T> {
    private final BehaviorController controller;
    private final Class<T> state;

    Then(final Class<T> state, final BehaviorController controller) {
        this.state = state;
        this.controller = controller;
    }

    public <V> Then<T> should(final ConditionMethod<T, V> condition) {
        controller.should(state, condition);
        return new Then<>(state, controller);
    }
}
