package com.github.thogr.bedede.examples;

import junit.framework.Assert;

import com.github.thogr.bedede.state.ConditionVerifier;
import com.github.thogr.bedede.state.Otherwise;

public class MyConditionVerifier implements ConditionVerifier<MyCondition<Boolean>> {

	@Override
	public void verify(MyCondition<Boolean> condition, Otherwise otherwise) {
		Assert.assertTrue(otherwise.getMessage(), condition.value());
	}

}
