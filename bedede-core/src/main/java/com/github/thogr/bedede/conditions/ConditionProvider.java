package com.github.thogr.bedede.conditions;


public interface ConditionProvider {
    ConditionVerifier<?> getVerifier(final Class<?> conditionClass);
}
