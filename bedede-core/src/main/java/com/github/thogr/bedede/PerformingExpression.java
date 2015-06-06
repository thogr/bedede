package com.github.thogr.bedede;

import com.github.thogr.bedede.ActionExpression;

public final class PerformingExpression<T> {
    ActionExpression<T> action;

    public PerformingExpression(ActionExpression<T> expr) {
        this.action = expr;
    }
}
