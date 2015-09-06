package com.github.thogr.bedede;

public interface StateBasedWhen<S> extends ThenExpecting<S>, ThenAnyTarget {

    /**
     * Continued with more actions.
     * @param action the action to be executed
     * @return the continued specification with more actions or verifications
     */
    StateBasedWhen<S> when(ActionExpression<S> action);
}
