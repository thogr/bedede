package com.github.thogr.bedede.conditions.internal;

import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Otherwise;


public class BooleanConditionVerifier implements ConditionVerifier<BooleanCondition> {

    @Override
    public Boolean verify(final BooleanCondition condition, final Otherwise otherwise) {
        if (!condition.value()) {
            throw new AssertionError(otherwise.getMessage());
        }
        return condition.value();
    }

}
