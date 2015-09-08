package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ThenExpecting;

public class AbstractStateControlled<S> {

    private final ThreadLocal<BehaviorController> controller = new ThreadLocal<>();
    private final Class<S> state;

     protected AbstractStateControlled(final Class<S> state, final BehaviorController controller) {
        this.state = state;
        this.controller.set(controller);
    }

    <T> ThenExpecting<T> thenState(final Class<T> target) {
        return getController().thenState(target);
    }

    BehaviorController getController() {
        return controller.get();
    }

    Class<S> getState() {
        return state;
    }

}
