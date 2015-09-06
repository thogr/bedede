package com.github.thogr.bedede.conditions;

/**
 * Provider of a condition verifier
 */
public interface ConditionProvider {

    /**
     * Returns the provided condition verifier for a particular condition class
     * @param conditionClass the class that has a condition provider
     * @return the condition verifier
     */
    ConditionVerifier<?> getVerifier(final Class<?> conditionClass);
}
