package com.github.thogr.bedede.core;

import java.util.function.BiFunction;

public interface SecondGiven<F, T1, T2>
extends SecondWith<F, T1, T2>,
        SecondWithBehaviorExpression<F, T1, T2> {

    /**
     * Similar to {@link com.github.thogr.bedede.core.WithBehaviorExpression#with(ActionExpression)}
     * but, action takes two parameters, since it will operate on the two objects in focus.
     * @param action the action to be performed
     * @return the continued behavior specification
     */
    SecondWith<F, T1, T2> with(BiActionExpression<? super T1, ? super T2> action);

    /**
     * Adds third object in focus which further when() and then() expressions will
     * operate on, alternatively the two current objects in focus. But only if they have a
     * {@link java.util.function.BiFunction} or {@link BiActionExpression}.
     * Note that other operations will now only operate on the new object in focus.
     * @param expr a function or lambda expression that returns the new object to add to focus
     * @param <S> the type of object to add to focus
     * @return the continued behavior expression
     */
    <S> ContinuedBehaviorExpression<S, T1, T2>
        given(BiFunction<? super T1, ? super T2, ? extends S> expr);

    /**
     * Alias for {@link #given(BiFunction)}
     * @param expr a function or lambda expression that returns the new object to add to focus
     * @param <S> the type of object to add to focus
     * @return the continued behavior expression
     */
    <S> ContinuedBehaviorExpression<S, T1, T2>
        and(BiFunction<? super T1, ? super T2, ? extends S> expr);

}
