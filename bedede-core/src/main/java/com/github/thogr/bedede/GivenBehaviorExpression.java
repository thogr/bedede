package com.github.thogr.bedede;

import java.util.function.BiFunction;

public interface GivenBehaviorExpression<T> extends BehaviorExpression<T> {

    /**
     * Adds another object in focus which further when() and then() expressions will
     * operate on, besides the current object in focus. But only if they have a
     * {@link BiFunction} or {@link BiActionExpression}.
     * Note that other operations will still operate on the previous object in focus.
     * @param other the object to add to focus
     * @param <T> the type of object or the starting environment
     * @param value initial value
     * @return the continued behavior expression
     */
    <T2> SecondBehaviorExpression<T, T2> given(T2 other);

    /**
     * Alias for {@link #given(Object)}
     * @param other
     * @return the continued behavior expression
     */
    <T2> SecondBehaviorExpression<T, T2> and(T2 other);

}
