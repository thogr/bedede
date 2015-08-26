package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.Assuming;
import com.github.thogr.bedede.StateBasedWhen;

class AssumingImpl<T> extends Assuming<T> {

    AssumingImpl(final Class<T> state, final BehaviorController controller) {
        super(state, controller);
    }

    @Override
    public StateBasedWhen<T> when(ActionExpression<T> action) {
        return whenAction(action);
    }
}
