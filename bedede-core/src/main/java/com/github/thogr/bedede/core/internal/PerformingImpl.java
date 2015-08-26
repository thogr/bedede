package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.Performing;

final class PerformingImpl<T> extends Performing<T> {
    private ActionExpression<T> action;
    private final int count;

    PerformingImpl(ActionExpression<T> expr) {
        this(expr, 1);
    }

    PerformingImpl(ActionExpression<T> action, int count) {
        this.action = action;
        this.count = count;
    }

    @Override
    void perform(T it) {
        for (int i = 1; i <= count; i++) {
            action.perform(it);
        }
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.Performing#times(int)
     */
    @Override
    public Performing<T> times(int n) {
        return new PerformingImpl<T>(action, n);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.Performing#twice()
     */
    @Override
    public Performing<T> twice() {
        return times(2);
    }

    protected static <T> void perform(AbstractPerformer<T> performer, T it) {
        performer.perform(it);
    }

}
