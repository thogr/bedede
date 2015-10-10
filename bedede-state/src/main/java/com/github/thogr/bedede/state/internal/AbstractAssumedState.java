package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.conditions.ExpectingExpression;
import com.github.thogr.bedede.state.ThenExpecting;

public class AbstractAssumedState<T> extends AbstractStateControlled<T> {

    protected AbstractAssumedState(final Class<T> state, final BehaviorController controller) {
       super(state, controller);
    }

    protected <V> ThenExpecting<T> thenExpecting(final ExpectingExpression<T, V> condition) {
        return getController().thenExpecting(getState(), condition);
    }
}
