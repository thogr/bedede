package com.github.thogr.bedede;

public interface TargetWhen<T, S> {

    /**
     * Verifies that the target state.
     * @param target the target state class
     * @return to be continued with more verifications
     */
    ThenExpecting<T> then(Class<T> target);

}
