package com.github.thogr.bedede.conditions.internal;

import com.github.thogr.bedede.conditions.internal.ConditionProvider;
import com.github.thogr.bedede.conditions.internal.ConditionVerifier;

public class ExpectedBooleanConditionProvider implements ConditionProvider {

    @Override
    public ConditionVerifier<?> getVerifier(final Class<?> conditionClass) {
        return new ExpectedBooleanConditionVerifier();
    }
}
