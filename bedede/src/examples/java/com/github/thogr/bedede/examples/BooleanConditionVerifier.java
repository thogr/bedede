package com.github.thogr.bedede.examples;

import org.junit.Assert;

import com.github.thogr.bedede.Otherwise;
import com.github.thogr.bedede.conditions.ConditionVerifier;

public class BooleanConditionVerifier implements ConditionVerifier<SimpleCondition<Boolean>> {

    @Override
    public void verify(final SimpleCondition<Boolean> condition, final Otherwise otherwise) {
        Assert.assertTrue(otherwise.getMessage(), condition.value());
    }

}
