package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.Entry;
import com.github.thogr.bedede.TargetAssuming;

public abstract class AbstractInternalEntry<S> extends StateBehavior<S> {

    private final Class<S> target;

    protected AbstractInternalEntry(final Class<S> target) {
        this.target = target;
    }

    protected AbstractInternalEntry() {
        this.target = TypeArguments.typeArgument(this);
    }

    Class<S> getTarget() {
        return target;
    }

    protected final <T> TargetAssuming<S, T> given(final Class<T> state) {
        return new TargetAssumingImpl<S, T>(target, getController().given(state));
    }

    protected final <T> TargetAssuming<S, T> given(final Entry<T> entry) {
        return new TargetAssumingImpl<S, T>(target, getController().given(entry));
    }

    protected final <T> TargetAssuming<S, T> assuming(final Class<T> state) {
        return new TargetAssumingImpl<S, T>(target, getController().assuming(state));
    }

    protected final void then(final Class<S> state) {
        getController().then(state);
    }

}
