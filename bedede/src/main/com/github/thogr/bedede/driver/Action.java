package com.github.thogr.bedede.driver;


/**
 * An action performed in a state
 * @author thogr
 *
 * @param <S> the state class where the action is performed
 */
public abstract class Action<S> {
	
	private BehaviorDriver bdd;

	protected BehaviorDriver getDriver() {
		return bdd;
	}

	protected void perform(BehaviorDriver bdd) {
		this.bdd = bdd;
		perform();
	}

	protected abstract void perform();

}