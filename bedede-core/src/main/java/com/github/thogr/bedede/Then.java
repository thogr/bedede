package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ExpectingExpression;

public class Then<T> {
    private final ThreadLocal<BehaviorController> controller = new ThreadLocal<>();
    private final Class<T> state;

    Then(final Class<T> state, final BehaviorController controller) {
        this.state = state;
        this.controller.set(controller);
    }

    public <V> Then<T> then(final ExpectingExpression<T, V> epression) {
        return controller.get().then(state, epression);
    }
}
