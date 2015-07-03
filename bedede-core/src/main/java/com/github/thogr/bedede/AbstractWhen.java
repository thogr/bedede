package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ExpectingExpression;

public class AbstractWhen<S> {

    protected final ThreadLocal<BehaviorController> controller = new ThreadLocal<>();
    protected final Class<S> state;

    AbstractWhen(final Class<S> state, final BehaviorController controller) {
        this.state = state;
        this.controller.set(controller);
    }

    public When<S> when(final ActionExpression<S> action) {
        return getController().when(action, state);
    }

    public <V> ThenExpecting<S> then(final ExpectingExpression<S, V> epression) {
        return getController().then(state, epression);
    }

    <T> ThenExpecting<T> thenState(final Class<T> target) {
        return getController().then(target);
    }

    BehaviorController getController() {
        return controller.get();
    }

}
