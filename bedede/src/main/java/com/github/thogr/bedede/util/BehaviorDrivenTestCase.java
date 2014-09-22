package com.github.thogr.bedede.util;

import com.github.thogr.bedede.driver.Action;
import com.github.thogr.bedede.driver.BehaviorDriver;
import com.github.thogr.bedede.driver.Given;
import com.github.thogr.bedede.state.Condition;
import com.github.thogr.bedede.state.ConditionVerifier;
import com.github.thogr.bedede.state.internal.Then;

public abstract class BehaviorDrivenTestCase {
	private BehaviorDriver bdd = new BehaviorDriver();

	protected final void given(Given<?> given) {
		bdd.given(given);
	}

	protected final <T> void when(Action<T> action) {
		bdd.when(action);
	}

	protected final void then(Class<?> state) {
		bdd.then(state);
	}

	protected final <T> void then(Condition<T> condition) {
		Then.then(condition, verifier());
	}

	protected abstract <T> ConditionVerifier<T> verifier();
	
}
