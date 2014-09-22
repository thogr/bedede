package com.github.thogr.bedede.driver;


/**
 * A number of steps to reach a a target state.
 * The method {@link #perform()} is the implementation.
 * Typically the first step is to refer to some other Given, 
 * by calling {@link #given(Given)},
 * and then some actions, and finally a call to {@link #then(Class)}
 * @author thogr
 *
 * @param <Target> the target state
 */
public abstract class Given<Target> {
	
	private BehaviorDriver bdd;

	public Given() {
	}
	
	void perform(BehaviorDriver bdd) {
		this.bdd = bdd;
		perform();
	}
	
	protected abstract void perform();

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
