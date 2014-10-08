package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ExpectingExpression;

public final class When<S> {

    private final BehaviorController controller;
    private final Class<S> state;

    When(final Class<S> state, final BehaviorController controller) {
        this.state = state;
        this.controller = controller;
    }

    public <V> Then<S> then(final ExpectingExpression<S, V> epression) {
        return then(state).then(epression);
    }

    public <T> Then<T> then(final Class<T> state) {
        return controller.then(state);
    }
}
