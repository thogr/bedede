package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.state.Condition;
import com.github.thogr.bedede.state.ConditionVerifier;
import com.github.thogr.bedede.state.Otherwise;

public class Expecting {

	public static <T> Condition<T> expecting(
			T condition, Otherwise otherwise) {
		return new Condition<T>() {
	
			@Override
			protected void verify(
					ConditionVerifier<T> verifier) {
				verifier.verify(condition, otherwise);
			}
		};
	}

}
