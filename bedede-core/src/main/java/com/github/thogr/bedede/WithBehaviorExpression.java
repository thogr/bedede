package com.github.thogr.bedede;

public interface WithBehaviorExpression<T> {

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
    BehaviorExpression<T> with(ActionExpression<? super T> action);
}
