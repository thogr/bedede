package com.github.thogr.bedede;

import java.util.function.Function;

abstract class Expressions {
    static <T> BehaviorExpression<T> given(final T obj) {
        return new BasicBehaviorExpression<T>(obj);
    }

    static <T> BehaviorExpression<T> given(final BehaviorExpression<T> expr) {
        return expr;
    }

    static <T> PerformingExpression<T> performing(final ActionExpression<T> expr) {
        return new PerformingExpression<T>(expr);
    }

    static <T> Function<T, T> it() {
        return (it -> it);
    }

    static <T,S> TransformingExpression<T, S> retrieving(Function<T, S> expr) {
        return transforming(expr);
    }

    static <T,S> TransformingExpression<T, S> transforming(Function<T, S> expr) {
        return new TransformingExpression<T, S>(expr);
    }
}
