package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.core.Performing;
import com.github.thogr.bedede.core.internal.PerformingAdapter;
import com.github.thogr.bedede.state.StateBasedWhen;
import com.github.thogr.bedede.state.ThenExpecting;

public class AbstractStateControlled<S> {

    private final ThreadLocal<BehaviorController> controller = new ThreadLocal<>();
    private final Class<S> state;

    protected AbstractStateControlled(final Class<S> state, final BehaviorController controller) {
        this.state = state;
        this.controller.set(controller);
    }

    protected StateBasedWhen<S> whenAction(final ActionExpression<S> action) {
        return getController().when(action, getState());
    }

    protected StateBasedWhen<S> whenPerforming(final Performing<S> action) {
        return whenAction(new PerformingAdapter<>(action));
    }

    <T> ThenExpecting<T> thenState(final WrappedState<T> target) {
        return thenState(target.getState());
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
