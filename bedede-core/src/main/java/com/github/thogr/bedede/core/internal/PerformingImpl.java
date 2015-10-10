package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.core.Performing;

final class PerformingImpl<T> extends Performing<T> {
    private ActionExpression<T> action;
    private final int count;

    PerformingImpl(final ActionExpression<T> expr) {
        this(expr, 1);
    }

    PerformingImpl(final ActionExpression<T> action, final int count) {
        this.action = action;
        this.count = count;
    }

    @Override
    void perform(final T it) {
        for (int i = 1; i <= count; i++) {
            action.perform(it);
        }
    }

    @Override
    public Performing<T> times(final int n) {
        return new PerformingImpl<T>(action, n);
    }

    @Override
    public Performing<T> twice() {
        return times(2);
    }
}
