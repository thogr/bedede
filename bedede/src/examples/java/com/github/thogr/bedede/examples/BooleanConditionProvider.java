package com.github.thogr.bedede.examples;

import com.github.thogr.bedede.conditions.ConditionProvider;
import com.github.thogr.bedede.conditions.ConditionVerifier;

public final class BooleanConditionProvider implements ConditionProvider<SimpleCondition<Boolean>> {

    @Override
    public ConditionVerifier<SimpleCondition<Boolean>> getVerifier(
            final Class<SimpleCondition<Boolean>> conditionClass) {
        return new BooleanConditionVerifier();
    }
}
