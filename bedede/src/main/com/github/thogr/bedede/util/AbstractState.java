package com.github.thogr.bedede.util;

import com.github.thogr.bedede.state.State;
import com.github.thogr.bedede.state.Verifyer;

@State(verifyer = AbstractState.StateVerifier.class)
public abstract class AbstractState {
	public static class StateVerifier implements Verifyer<AbstractState> {

		@Override
		public void verify(AbstractState state) {
			state.verify();
		}
	}
	
	protected abstract void verify();
}