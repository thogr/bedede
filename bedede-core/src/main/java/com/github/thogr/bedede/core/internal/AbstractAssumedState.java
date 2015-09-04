package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.StateBasedWhen;
import com.github.thogr.bedede.ThenExpecting;
import com.github.thogr.bedede.conditions.ExpectingExpression;

public class AbstractAssumedState<T> extends AbstractStateControlled<T> {

    protected AbstractAssumedState(final Class<T> state, final BehaviorController controller) {
       super(state, controller);
    }

    protected <V> ThenExpecting<T> thenExpecting(final ExpectingExpression<T, V> condition) {
        return getController().then(getState(), condition);
    }

    protected StateBasedWhen<T> whenAction(final ActionExpression<T> action) {
        return getController().when(action, getState());
    }
}
