package com.github.thogr.bedede.state;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.state.internal.AbstractAssumedState;
import com.github.thogr.bedede.state.internal.BehaviorController;

public abstract class Assuming<T> extends AbstractAssumedState<T> implements ThenExpecting<T> {

    protected Assuming(final Class<T> state, final BehaviorController controller) {
        super(state, controller);
    }

    /**
     * Specifies the action to be executed in a test.
     * @param action the action to be executed
     * @return the continued specification
     */
    public abstract StateBasedWhen<T> when(final ActionExpression<T> action);
}
