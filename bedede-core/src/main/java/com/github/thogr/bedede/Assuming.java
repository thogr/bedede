package com.github.thogr.bedede;

import com.github.thogr.bedede.core.internal.AbstractAssumedState;
import com.github.thogr.bedede.core.internal.BehaviorController;

public abstract class Assuming<T> extends AbstractAssumedState<T> {

    protected Assuming(Class<T> state, BehaviorController controller) {
        super(state, controller);
    }

    public abstract StateBasedWhen<T> when(final ActionExpression<T> action);

}
