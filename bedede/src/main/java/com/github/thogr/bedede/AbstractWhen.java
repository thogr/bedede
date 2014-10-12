package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ExpectingExpression;

public class AbstractWhen<S> {

    protected final ThreadLocal<BehaviorController> controller = new ThreadLocal<>();
    protected final Class<S> state;

    AbstractWhen(final Class<S> state, final BehaviorController controller) {
        this.state = state;
        this.controller.set(controller);
    }

    public <V> Then<S> then(final ExpectingExpression<S, V> epression) {
        return thenState(state).then(epression);
    }

    protected <T> Then<T> thenState(final Class<T> target) {
        return getController().then(target);
    }

    protected BehaviorController getController() {
        return controller.get();
    }

}
