package com.github.thogr.bedede;

import com.github.thogr.bedede.annotations.State;


@State(verifier = AbstractState.DefaltStateVerifier.class)
public abstract class AbstractState {
    public static class DefaltStateVerifier implements StateVerifier<AbstractState> {

        @Override
        public void verify(final AbstractState state) {
            state.onEntry();
        }
    }

    protected abstract void onEntry();
}
