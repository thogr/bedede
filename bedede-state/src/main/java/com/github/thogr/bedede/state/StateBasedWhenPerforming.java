package com.github.thogr.bedede.state;

import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.core.Performing;

public interface StateBasedWhenPerforming<T> {
     /**
     * Specifies the action to be executed in a test.
     * @param action the action to be executed
     * @return the continued specification
     */
    StateBasedWhen<T> when(final Performing<T> action);

    @Deprecated
    StateBasedWhen<T> when(ActionExpression<T> action);
}
