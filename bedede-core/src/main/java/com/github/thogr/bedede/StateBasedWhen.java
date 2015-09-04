package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ExpectingExpression;

public interface StateBasedWhen<S> {

    /**
     * Verifies that the target state.
     * @param target the target state class
     * @param <T> the type of the target state class
     * @return to be continued with more verifications
     */
    <T> ThenExpecting<T> then(Class<T> target);

    /**
     * Continued with more actions.
     * @param action
     * @return
     */
    StateBasedWhen<S> when(ActionExpression<S> action);

    <V> ThenExpecting<S> then(final ExpectingExpression<S, V> epression);
}
