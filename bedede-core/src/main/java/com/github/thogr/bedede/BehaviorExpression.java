package com.github.thogr.bedede;

import static org.junit.Assert.assertThat;

import java.util.function.Function;

import org.hamcrest.Matcher;

public final class BehaviorExpression<T> {
    private T obj;

    BehaviorExpression(final T obj) {
        this.obj = obj;
    }

    public BehaviorExpression<T> when(final NonFunctional<? super T> expr) {
        expr.action.perform(obj);
        return new BehaviorExpression<T>(obj);
    }

    public <S> BehaviorExpression<S> when(final Functional<? super T, S> expr) {
        S result = expr.function.apply(obj);
        return new BehaviorExpression<S>(result);
    }

    public <S> BehaviorExpression<T> then(
            final Function<? super T, S> it, final Matcher<? super S> is) {
        S result = it.apply(obj);
        assertThat(result, is);
        return new BehaviorExpression<T>(obj);
    }
}
