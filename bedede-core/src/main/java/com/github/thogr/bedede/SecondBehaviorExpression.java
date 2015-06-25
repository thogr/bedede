package com.github.thogr.bedede;
import static org.junit.Assert.assertThat;

import java.util.function.BiFunction;

import org.hamcrest.Matcher;

public class SecondBehaviorExpression<T1, T2> extends GivenBehaviorExpression<T1> {

    private T1 first;
    private T2 second;

    SecondBehaviorExpression(T1 first, T2 second) {
        super(first);
        this.first = first;
        this.second = second;
    }

    public final <S> SecondBehaviorExpression<T1, T2> then(
            final BiFunction<? super T1, ? super T2, S> it, final Matcher<? super S> is) {
        S result = it.apply(first, second);
        assertThat(result, is);
        return new SecondBehaviorExpression<T1, T2>(first, second);
    }

    public final SecondBehaviorExpression<T1, T2> when(
            final BiActionExpression<? super T1, ? super T2> action) {
        action.perform(first, second);
        return new SecondBehaviorExpression<T1, T2>(first, second);
    }

    public final SecondBehaviorExpression<T1, T2> when(
            final BiPerformingExpression<? super T1, ? super T2> expr) {
        return when(expr.action);
    }
}
