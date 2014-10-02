package com.github.thogr.bedede;

public interface ConditionProvider {
    <V> ConditionVerifier<V> getVerifier(final Class<V> conditionClass);
}
