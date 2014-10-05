package com.github.thogr.bedede.conditions;

import org.junit.Assert;

import com.github.thogr.bedede.Otherwise;

public class BooleanConditionVerifier implements ConditionVerifier<BooleanCondition> {

    @Override
    public void verify(final BooleanCondition condition, final Otherwise otherwise) {
        Assert.assertTrue(otherwise.getMessage(), condition.value());
    }

}
