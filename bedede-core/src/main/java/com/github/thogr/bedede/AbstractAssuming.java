package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ExpectingExpression;

abstract class AbstractAssuming<T> {

    private final ThreadLocal<BehaviorController> controller = new ThreadLocal<>();
    protected final Class<T> state;

    AbstractAssuming(final Class<T> state, final BehaviorController controller) {
        this.controller.set(controller);
        this.state = state;
    }

    public <V> ThenExpecting<T> then(final ExpectingExpression<T, V> condition) {
        return getController().then(state, condition);
    }

    protected BehaviorController getController() {
        return controller.get();
    }

}
