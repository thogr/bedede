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
	
	public <T> void when(Action<T> action) {
		action.perform(this);
	}

	public void then(Class<?> state) {
		machine.next(state);
	}
}
