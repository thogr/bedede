package com.github.thogr.bedede;

import java.util.function.BiFunction;

import org.hamcrest.Matcher;

public interface SecondBehaviorExpression<T1, T2> extends GivenBehaviorExpression<T1> {

    <S> SecondBehaviorExpression<T1, T2> then(
            BiFunction<? super T1, ? super T2, S> it, Matcher<? super S> is);

    SecondBehaviorExpression<T1, T2> when(
            BiActionExpression<? super T1, ? super T2> action);

    SecondBehaviorExpression<T1, T2> when(
            BiPerformingExpression<? super T1, ? super T2> expr);

    <S> BehaviorExpression<S> when(
            BiTransformingExpression<? super T1, ? super T2, ? extends S> expr);

}
