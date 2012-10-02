package com.github.thogr.bedede.driver;


public abstract class Action<Before, After> {
	
	private BehaviorDriver bdd;

	void perform(BehaviorDriver bdd, Before state) {
		this.bdd = bdd;
		perform(state);
	}
	
	protected abstract void perform(Before state);
	
	protected void then(Class<After> next) {
		bdd.then(next);
	}
}
