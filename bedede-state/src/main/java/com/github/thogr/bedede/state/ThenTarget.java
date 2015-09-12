package com.github.thogr.bedede.state;


public interface ThenTarget<T> {

    /**
     * Verifies the target state.
     * @param target the target state class
     * @return the continued specification with more verifications
     */
    ThenExpecting<T> then(Class<T> target);

}
