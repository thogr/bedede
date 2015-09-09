package com.github.thogr.bedede.conditions.internal;

import com.github.thogr.bedede.conditions.Otherwise;


public interface ConditionVerifier <T> {

    /**
     * Verifies the condition object.
     * @param condition the condition object
     * @param otherwise the description of the unexpected
     * @return an object that may only be available, when the condition is satisfied
     */
    Object verify(T condition, Otherwise otherwise);
}
