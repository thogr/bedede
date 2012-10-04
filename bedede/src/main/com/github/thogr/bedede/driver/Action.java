package com.github.thogr.bedede.driver;

import com.github.thogr.bedede.state.InternalState;


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

	protected void perform(BehaviorDriver bdd, S state) {
		this.bdd = bdd;
		perform(state);
	}

	protected abstract void perform(S state);

	protected ActionPerformer<S> when(InternalState<S> state) {
		return bdd.when(state);
	}
}
