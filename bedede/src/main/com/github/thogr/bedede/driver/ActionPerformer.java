package com.github.thogr.bedede.driver;


public class ActionPerformer<Current> {
	private final Current current;
	private final BehaviorDriver bdd;

	public ActionPerformer(BehaviorDriver bdd, Current current) {
		this.bdd = bdd;
		this.current = current;
	}
	
	public void perform(Action<Current, ?> action) {
		action.perform(bdd, current);
	}
	
	public Current perform() {
		return current;
	}
}
