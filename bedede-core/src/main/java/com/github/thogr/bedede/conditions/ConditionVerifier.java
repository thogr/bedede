package com.github.thogr.bedede.conditions;


public interface ConditionVerifier <T> {

    /**
     * Verifies the condition object.
     * @param condition the condition object
     * @param otherwise the description of the unexpected
     * @return an object that may only be available, when the condition is satisfied
     */
    Object verify(T condition, Otherwise otherwise);
}
