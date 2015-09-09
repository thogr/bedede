package com.github.thogr.bedede.conditions;


public class BooleanConditionVerifier implements ConditionVerifier<BooleanCondition> {

    @Override
    public Boolean verify(final BooleanCondition condition, final Otherwise otherwise) {
        if (!condition.value()) {
            throw new AssertionError(otherwise.getMessage());
        }
        return condition.value();
    }

}
