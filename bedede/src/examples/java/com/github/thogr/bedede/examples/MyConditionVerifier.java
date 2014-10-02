package com.github.thogr.bedede.examples;

import org.junit.Assert;

import com.github.thogr.bedede.ConditionVerifier;
import com.github.thogr.bedede.Otherwise;

public class MyConditionVerifier implements ConditionVerifier<MyCondition<Boolean>> {

    @Override
    public void verify(final MyCondition<Boolean> condition, final Otherwise otherwise) {
        Assert.assertTrue(otherwise.getMessage(), condition.value());
    }

}
