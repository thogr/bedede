package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.conditions.ExpectingExpression;
import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.core.Performing;
import com.github.thogr.bedede.state.StateBasedWhen;
import com.github.thogr.bedede.state.ThenExpecting;

abstract class AbstractWhenImpl<S> extends AbstractStateControlled<S> {

    AbstractWhenImpl(final Class<S> state, final BehaviorController controller) {
        super(state, controller);
    }

    <V> ThenExpecting<S> thenExpecting(final ExpectingExpression<S, V> expression) {
        return getController().thenExpecting(getState(), expression);
    }

    public StateBasedWhen<S> when(final ActionExpression<S> action) {
        return whenAction(action);
    }

    public StateBasedWhen<S> when(final Performing<S> action) {
        return whenPerforming(action);
    }
}
