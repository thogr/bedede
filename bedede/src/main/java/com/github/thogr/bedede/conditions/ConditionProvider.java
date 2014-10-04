package com.github.thogr.bedede.conditions;


public interface ConditionProvider<V> {
    ConditionVerifier<V> getVerifier(final Class<V> conditionClass);
}
