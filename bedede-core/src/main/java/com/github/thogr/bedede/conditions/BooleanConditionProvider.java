package com.github.thogr.bedede.conditions;


public final class BooleanConditionProvider implements ConditionProvider {

    @Override
    public ConditionVerifier<?> getVerifier(final Class<?> conditionClass) {
        return new BooleanConditionVerifier();
    }
}
