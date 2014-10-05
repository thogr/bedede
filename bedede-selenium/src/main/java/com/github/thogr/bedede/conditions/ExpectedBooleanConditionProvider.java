package com.github.thogr.bedede.conditions;

public class ExpectedBooleanConditionProvider implements ConditionProvider {

    @Override
    public ConditionVerifier<?> getVerifier(final Class<?> conditionClass) {
        return new ExpectedBooleanConditionVerifier();
    }
}
