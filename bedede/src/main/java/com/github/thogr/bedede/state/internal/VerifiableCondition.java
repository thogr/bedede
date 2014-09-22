package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.state.ConditionVerifier;

public abstract class VerifiableCondition<T> {

	protected VerifiableCondition() {
	}

	protected abstract void verify(ConditionVerifier<T> verifier);

}