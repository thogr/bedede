package com.github.thogr.bedede;

public final class BiPerformingExpression<T1, T2> {
    BiActionExpression<T1, T2> action;

    public BiPerformingExpression(BiActionExpression<T1, T2> expr) {
        this.action = expr;
    }
}
