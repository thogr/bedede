package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.StateBasedWhen;
import com.github.thogr.bedede.ThenExpecting;
import com.github.thogr.bedede.conditions.ExpectingExpression;

final class StateBasedWhenImpl<S> extends AbstractWhenImpl<S> implements StateBasedWhen<S> {

    StateBasedWhenImpl(final Class<S> state, final BehaviorController controller) {
        super(state, controller);
    }

    @Override
    public <T> ThenExpecting<T> then(final Class<T> target) {
        return thenState(target);
    }

    @Override
    public <V> ThenExpecting<S> then(final ExpectingExpression<S, V> expression) {
        return thenExpecting(expression);
    }

    @Override
    public StateBasedWhen<S> when(final ActionExpression<S> action) {
        return whenAction(action);
    }
}
