package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.core.BiActionExpression;

public class AbstractBiPerformer<T1, T2> {

    private final BiActionExpression<T1, T2> action;

    protected AbstractBiPerformer(final BiActionExpression<T1, T2> expr) {
        this.action = expr;
    }

    BiActionExpression<T1, T2> getAction() {
        return action;
    }
}
