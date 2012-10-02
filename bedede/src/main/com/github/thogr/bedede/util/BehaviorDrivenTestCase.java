package com.github.thogr.bedede.util;

import com.github.thogr.bedede.driver.ActionPerformer;
import com.github.thogr.bedede.driver.BehaviorDriver;
import com.github.thogr.bedede.driver.Given;

public class BehaviorDrivenTestCase {
	private BehaviorDriver bdd = new BehaviorDriver();
	
	public <T> ActionPerformer<T> when(Class<T> state) {
		return bdd.when(state);
	}

	public void then(Class<?> state) {
		bdd.then(state);
	}

	public void given(Given<?> given) {
		bdd.given(given);
	}
}
