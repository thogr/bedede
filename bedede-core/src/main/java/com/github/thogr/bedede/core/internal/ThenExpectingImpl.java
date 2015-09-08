package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ThenExpecting;
import com.github.thogr.bedede.conditions.ExpectingExpression;

public class ThenExpectingImpl<T> implements ThenExpecting<T> {
    private final ThreadLocal<BehaviorController> controller = new ThreadLocal<>();
    private final Class<T> state;

    ThenExpectingImpl(final Class<T> state, final BehaviorController controller) {
        this.state = state;
        this.controller.set(controller);
    }

    @Override
    public <V> ThenExpecting<T> then(final ExpectingExpression<T, V> epression) {
        return controller.get().thenExpecting(state, epression);
    }
}
