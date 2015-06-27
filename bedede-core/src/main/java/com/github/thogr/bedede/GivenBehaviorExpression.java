package com.github.thogr.bedede;

import java.util.function.BiFunction;

public interface GivenBehaviorExpression<T> extends BehaviorExpression<T> {

    /**
     * Adds another object in focus which further when() and then() expressions will
     * operate on, besides the current object in focus. But only if they have a
     * {@link BiFunction} or {@link BiActionExpression}.
     * Note that other operations will still operate on the previous object in focus.
     * @param other the object to add to focus
     * @param <T2> the type of object to add to focus
     * @return the continued behavior expression
     */
    <T2> SecondBehaviorExpression<T, T2> given(T2 other);

    /**
     * Alias for {@link #given(Object)}
     * @param other the object to add to focus
     * @param <T2> the type of object to add to focus
     * @return the continued behavior expression
     */
    <T2> SecondBehaviorExpression<T, T2> and(T2 other);

    /**
     * When performing an action on the current object. Intended to be more for initializing
     * the object than actually performing the actual main action of the test.
     * The behavior of the expression given(object).with(action) though, is exactly the same as
     * given(object).when(performing(action)).
     *
     * <p>
     * Example:
     * <pre>
     *   given(new Person()).with(it -> {
     *       it.setFirstName("John");
     *       it.setFamilyName("Smith");
     *   })
     *   .when(retrieving(Person::getFullName))
     *   .then(it(), is("John Smith"));
     * </pre>
     * </p>
     * @see #when(PerformingExpression)
     * @param action the action to be performed
     * @return a new expression where the current object in focus is the same
     */
    GivenBehaviorExpression<T> with(ActionExpression<? super T> action);

}
