package com.github.thogr.bedede.core;

public interface WithBehaviorExpression<T> {

    /**
     * When performing an action on the current object. Intended to be more for initializing
     * the object than actually performing the actual main action of the test.
     * The expression given(an(object)).with(action) though, will behave exactly the same as
     * given(an(object)).when(performing(action)).
     *
     * <p>
     * Example:
     * </p>
     * <pre>
     *   given(a(new Person())).with(it -&gt; {
     *       it.setFirstName("John");
     *       it.setFamilyName("Smith");
     *   })
     *   .when(retrieving(Person::getFullName))
     *   .then(it(), is("John Smith"));
     * </pre>
     * @see com.github.thogr.bedede.core.BehaviorExpression#when(PerformingExpression)
     * @param action the action to be performed
     * @return a new expression where the current object in focus is the same
     */
    With<T> with(ActionExpression<? super T> action);
}
