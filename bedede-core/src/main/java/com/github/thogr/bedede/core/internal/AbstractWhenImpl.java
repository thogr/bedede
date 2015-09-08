package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.StateBasedWhen;
import com.github.thogr.bedede.ThenExpecting;
import com.github.thogr.bedede.conditions.ExpectingExpression;

abstract class AbstractWhenImpl<S> extends AbstractStateControlled<S> {

    AbstractWhenImpl(final Class<S> state, final BehaviorController controller) {
        super(state, controller);
    }

    StateBasedWhen<S> whenAction(final ActionExpression<S> action) {
        return getController().when(action, getState());
    }

    <V> ThenExpecting<S> thenExpecting(final ExpectingExpression<S, V> expression) {
        return getController().thenExpecting(getState(), expression);
    }
}
