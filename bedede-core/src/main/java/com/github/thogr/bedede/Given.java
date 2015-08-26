package com.github.thogr.bedede;

import java.util.function.BiFunction;

public interface Given<T>
    extends BehaviorExpression<T>, WithBehaviorExpression<T> {

    <S> Given<T> given(final Behavior<S> expr);
    <S> Given<T> and(final Behavior<S> expr);

    /**
     * Adds another object in focus which further when() and then() expressions will
     * operate on, besides the current object in focus. But only if they have a
     * {@link BiFunction} or {@link BiActionExpression}.
     * Note that other operations will still operate on the previous object in focus.
     * @param other the object to add to focus
     * @param <T2> the type of object to add to focus
     * @return the continued behavior expression
     */
    <T2> SecondGiven<T, T2> given(AnObject<T2> other);

    /**
     * Alias for {@link #given(AnObject)}
     * @param other the object to add to focus
     * @param <T2> the type of object to add to focus
     * @return the continued behavior expression
     */
    <T2> SecondGiven<T, T2> and(AnObject<T2> other);
}
