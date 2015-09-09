package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.conditions.ExpectingExpression;
import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.state.Assuming;
import com.github.thogr.bedede.state.StateBasedWhen;
import com.github.thogr.bedede.state.ThenExpecting;

class AssumingImpl<T> extends Assuming<T> {

    AssumingImpl(final Class<T> state, final BehaviorController controller) {
        super(state, controller);
    }

    @Override
    public StateBasedWhen<T> when(final ActionExpression<T> action) {
        return whenAction(action);
    }

    @Override
    public <V> ThenExpecting<T> then(final ExpectingExpression<T, V> condition) {
        return thenExpecting(condition);
    }
}
