package com.github.thogr.bedede.util;

import com.github.thogr.bedede.driver.Action;
import com.github.thogr.bedede.driver.BehaviorDriver;
import com.github.thogr.bedede.driver.Given;

public class BehaviorDrivenTestCase {
	private BehaviorDriver bdd = new BehaviorDriver();

	public void given(Given<?> given) {
		bdd.given(given);
	}

	public <T> void when(Action<T> action) {
		bdd.when(action);
	}

	public void then(Class<?> state) {
		bdd.then(state);
	}
	
}
