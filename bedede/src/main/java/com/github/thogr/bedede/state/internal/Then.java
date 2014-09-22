package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.state.Condition;
import com.github.thogr.bedede.state.ConditionVerifier;

public final class Then {
	public static <T> void then(Condition<T> condition, ConditionVerifier<T> verifier) {
		condition.verify(verifier);
	}

}
