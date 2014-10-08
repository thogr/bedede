package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ExpectingExpression;

public final class Assuming<T> {

    private final BehaviorController controller;
    private final Class<T> state;

    Assuming(final Class<T> state, final BehaviorController controller) {
        this.controller = controller;
        this.state = state;
    }

    public When<T> when(final ActionExpression<T> action) {
        return controller.when(action, state);
    }

    public <V> Then<T> then(final ExpectingExpression<T, V> condition) {
        return controller.then(state).then(condition);
    }
}
