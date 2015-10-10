package com.github.thogr.bedede.state;

import com.github.thogr.bedede.state.internal.AbstractAssumedState;
import com.github.thogr.bedede.state.internal.BehaviorController;

public abstract class Assuming<T> extends AbstractAssumedState<T>
implements ThenExpecting<T>, StateBasedWhenPerforming<T> {

    protected Assuming(final Class<T> state, final BehaviorController controller) {
        super(state, controller);
    }

}
