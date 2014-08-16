package com.github.thogr.bedede.util;

import com.github.thogr.bedede.state.State;
import com.github.thogr.bedede.state.StateVerifier;

@State(verifyer = AbstractState.DefaltStateVerifier.class)
public abstract class AbstractState {
	public static class DefaltStateVerifier implements StateVerifier<AbstractState> {

		@Override
		public void verify(AbstractState state) {
			state.onEntry();
		}
	}
	
	protected abstract void onEntry();
}