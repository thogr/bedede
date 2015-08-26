package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.Entry;
import com.github.thogr.bedede.TargetAssuming;

public abstract class AbstractInternalEntry<S> extends StateBehavior<S> {

    protected final Class<S> target;

    public AbstractInternalEntry(final Class<S> target) {
        this.target = target;
    }

    public AbstractInternalEntry() {
        this.target = TypeArguments.typeArgument(this);
    }

    protected Class<S> getTarget() {
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
