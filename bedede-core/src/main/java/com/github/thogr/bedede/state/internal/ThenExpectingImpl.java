package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.conditions.ExpectingExpression;
import com.github.thogr.bedede.state.ThenExpecting;

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
