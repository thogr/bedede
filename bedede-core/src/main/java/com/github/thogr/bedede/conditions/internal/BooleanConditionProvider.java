package com.github.thogr.bedede.conditions.internal;


public final class BooleanConditionProvider implements ConditionProvider {

    @Override
    public ConditionVerifier<?> getVerifier(final Class<?> conditionClass) {
        return new BooleanConditionVerifier();
    }
}
