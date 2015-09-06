package com.github.thogr.bedede;

import com.github.thogr.bedede.conditions.ExpectingExpression;

public interface ThenExpecting<T> {

    /**
     * Specifies a condition to be verified in the test.
     * @param condition the condition to be verified.
     * @param <V> the type of the condition
     * @return the continued specification
     */
    <V> ThenExpecting<T> then(ExpectingExpression<T, V> condition);
}
