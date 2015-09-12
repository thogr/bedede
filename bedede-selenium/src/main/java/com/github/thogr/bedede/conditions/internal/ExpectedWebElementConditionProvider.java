package com.github.thogr.bedede.conditions.internal;

public class ExpectedWebElementConditionProvider implements ConditionProvider {

    @Override
    public ConditionVerifier<?> getVerifier(final Class<?> conditionClass) {
        return new ExpectedWebElementConditionVerifier();
    }

}
