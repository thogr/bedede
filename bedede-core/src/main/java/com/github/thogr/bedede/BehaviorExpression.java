package com.github.thogr.bedede;

/**
 * A BDD expression, e.g. given(...), or given(...).when(...), or given(...).when(...).then(...)
 *
 * @param <T> the type of object the actions operate on in the when() or then() expressions.
 */
public abstract class BehaviorExpression<T> extends Behavior<T> {

    BehaviorExpression(final T obj) {
        super(obj);
    }

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
    public final <S> BehaviorExpression<S> when(final TransformingExpression<? super T, ? extends S> expr) {
        S result = expr.function.apply(obj);
        return new BasicBehaviorExpression<S>(result);
    }

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
    public final WhenBehaviorExpression<T> when(final PerformingExpression<? super T> expr) {
        expr.perform(obj);
        return new WhenBehaviorExpression<T>(obj, expr);
    }

    /**
     * When performing an action on the current object.
     * The expression when(action) is exactly the same as when(performing(action)).
     * The expression of the form when(action) is more compact, but when(performing(action)) is
     * probably more easy to read grammatically correct. I all depends on the names of the methods
     * called in the action.
     * <p>
     * Example:<code>when(it -&gt; it.willStart())</code> may read well,
     * but <code>when(it -&gt; it.start())</code> may not,
     * thus consider <code>when(performing(the -&gt; the.start()))</code>
     * </p>
     * @see #when(PerformingExpression)
     * @param action the action to be performed
     * @return a new expression where the current object in focus is the same
     */
    public final WhenBehaviorExpression<T> when(final ActionExpression<? super T> action) {
        action.perform(obj);
        return new WhenBehaviorExpression<T>(obj, Expressions.performing(action));
    }

}
