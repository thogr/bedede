package com.github.thogr.bedede.state;


public interface ThenAnyTarget {

    /**
     * Verifies the target state.
     * @param target the target state class
     * @param <T> the type of the target state class
     * @return the continued specification with more verifications
     */
    <T> ThenExpecting<T> then(Class<T> target);
}
