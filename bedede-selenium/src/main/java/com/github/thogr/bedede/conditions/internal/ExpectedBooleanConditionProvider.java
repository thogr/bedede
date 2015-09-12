package com.github.thogr.bedede.conditions.internal;

public class ExpectedBooleanConditionProvider implements ConditionProvider {

    @Override
    public ConditionVerifier<?> getVerifier(final Class<?> conditionClass) {
        return new ExpectedBooleanConditionVerifier();
    }
}
