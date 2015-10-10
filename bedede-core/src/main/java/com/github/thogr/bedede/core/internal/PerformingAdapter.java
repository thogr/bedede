package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.core.ActionExpression;

public class PerformingAdapter<T> implements ActionExpression<T> {
    private final AbstractPerformer<T> performer;

    public PerformingAdapter(final AbstractPerformer<T> performer) {
        this.performer = performer;
    }

    @Override
    public void perform(final T it) {
        performer.perform(it);
    }
}
