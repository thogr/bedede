package com.github.thogr.bedede.driver;

import com.github.thogr.bedede.state.DefaultStateFactory;
import com.github.thogr.bedede.state.StateFactory;
import com.github.thogr.bedede.state.StateMachine;


public final class BehaviorDriver {

	private StateMachine machine;
	
	public BehaviorDriver(StateFactory factory) {
		this.machine = new StateMachine(factory);
	}
	
	public BehaviorDriver() {
		this(new DefaultStateFactory());
	}
	
	public void given(Given<?> given) {
		given.perform(this);
	}

	public <T> ActionPerformer<T> when(Class<T> state) {
		T current = machine.verify(state);
		return new ActionPerformer<T>(this, current);
	}
	
	public void then(Class<?> state) {
		machine.next(state);
	}
}
