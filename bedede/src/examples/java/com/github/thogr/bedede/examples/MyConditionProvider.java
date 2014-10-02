package com.github.thogr.bedede.examples;

import com.github.thogr.bedede.ConditionProvider;
import com.github.thogr.bedede.ConditionVerifier;

public final class MyConditionProvider implements ConditionProvider {

    @SuppressWarnings("unchecked")
    @Override
    public <V> ConditionVerifier<V> getVerifier(final Class<V> conditionClass) {
        return (ConditionVerifier<V>) new MyConditionVerifier();
    }
}
