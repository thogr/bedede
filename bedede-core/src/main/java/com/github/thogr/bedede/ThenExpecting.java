package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ExpectingExpression;

public class ThenExpecting<T> {
    private final ThreadLocal<BehaviorController> controller = new ThreadLocal<>();
    private final Class<T> state;

    ThenExpecting(final Class<T> state, final BehaviorController controller) {
        this.state = state;
        this.controller.set(controller);
    }

    public <V> ThenExpecting<T> then(final ExpectingExpression<T, V> epression) {
        return controller.get().then(state, epression);
    }
}
