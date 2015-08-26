package com.github.thogr.bedede;

public interface WhenBehavior<T> {

    /**
     * When transforming the current object to another object. The action is functional, i.e.
     * with a return value. Further behavior operates on that returned object, which will be the
     * new current object.
     * <p>
     * Example:
     * </p>
     * <pre>
     *  given(new Integer(123))
     *   .when(transforming(it -&gt; it.toString()))
     *   .then(it(), is(equalTo("123")));
     *  </pre>
     * @param <S> the type of object returned by the action
     * @param expr the the action to be performed, wrapped like:<code> transforming(action) </code>
     * @return a new expression where the object in focus is the result of the transformation
     */
    <S> TransformedBehaviorExpression<T, S> when(
            Transforming<? super T, ? extends S> expr);

    /**
     * When performing an action on the current object. The action is non-functional,
     * i.e. does not return anything. Further behavior operates on the same current object.
     * <p>
     * Example:
     * </p>
     * <pre>
     * when(performing(the -&gt; the.operation()))
     * </pre>
     * @param expr an action wrapped like: <code>performing(action)</code>
     * @return a new expression where the current object in focus is the same
     */
    WhenBehaviorExpression<T> when(Performing<T> expr);

}
