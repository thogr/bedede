package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.core.ActionExpression;

public abstract class WrappedPerformable<T> extends Wrapped<T> {

    protected WrappedPerformable(final T obj) {
        super(obj);
    }

    protected void perform(final ActionExpression<? super T> action) {
        action.perform(getWrapped());
    }

}
