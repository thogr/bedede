package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.StateBasedWhen;
import com.github.thogr.bedede.ThenExpecting;
import com.github.thogr.bedede.conditions.ExpectingExpression;

public abstract class AbstractWhenImpl<S> extends AbstractStateControlled<S> {

    AbstractWhenImpl(final Class<S> state, final BehaviorController controller) {
        super(state, controller);
    }

    public StateBasedWhen<S> when(final ActionExpression<S> action) {
        return getController().when(action, getState());
    }

    public <V> ThenExpecting<S> then(final ExpectingExpression<S, V> epression) {
        return getController().then(getState(), epression);
    }



}
