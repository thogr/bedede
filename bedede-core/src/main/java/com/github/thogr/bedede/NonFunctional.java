package com.github.thogr.bedede;

import com.github.thogr.bedede.ActionExpression;

public final class NonFunctional<T> {
    ActionExpression<T> action;

    public NonFunctional(ActionExpression<T> expr) {
        this.action = expr;
    }
}
