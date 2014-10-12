package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ExpectingExpression;

public final class When<S> {

    private final ThreadLocal<BehaviorController> controller = new ThreadLocal<>();
    private final Class<S> state;

    When(final Class<S> state, final BehaviorController controller) {
        this.state = state;
        this.controller.set(controller);
    }

    public <V> Then<S> then(final ExpectingExpression<S, V> epression) {
        return then(state).then(epression);
    }

    public <T> Then<T> then(final Class<T> target) {
        return controller.get().then(target);
    }
}
