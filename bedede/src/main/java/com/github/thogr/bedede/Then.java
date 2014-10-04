package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ConditionExpression;

public class Then<T> {
    private final BehaviorController controller;
    private final Class<T> state;

    Then(final Class<T> state, final BehaviorController controller) {
        this.state = state;
        this.controller = controller;
    }

    public <V> Then<T> should(final ConditionExpression<T, V> condition) {
        controller.should(state, condition);
        return new Then<>(state, controller);
    }
}
