package com.github.thogr.bedede;

import static org.junit.Assert.assertThat;

import java.util.function.Function;

import org.hamcrest.Matcher;
/**
 * A BDD expression, e.g. given(...), or given(...).when(...), or given(...).when(...).then(...)
 *
 * @param <T> the type of object the actions operate on in the when() or then() expressions.
 */
public abstract class BehaviorExpression<T> {
    protected T obj;

    BehaviorExpression(final T obj) {
        this.obj = obj;
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
    public final <S> BehaviorExpression<S> when(final TransformingExpression<? super T, S> expr) {
        S result = expr.function.apply(obj);
        return new BasicBehaviorExpression<S>(result);
    }

    /**
     * When performing an action on the current object. The action is non-functional, i.e. does not return
     * anything. Further behavior operates on the same current object.
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
        return when(expr.action);
    }

    /**
     * When performing an action on the current object.
     * The expression when(action) is exactly the same as when(performing(action)).
     * The expression of the form when(action) is more compact, but when(performing(action)) is
     * probably more easy to read grammatically correct. I all depends on the names of the methods
     * called in the action.
     * <p>
     * Example:<code>when(it -&gt; it.willStart())</code> may read well, but <code>when(it -&gt; it.start())</code> may not,
     * thus consider <code>when(performing(the -&gt; the.start()))</code>
     * </p>
     * @see #when(PerformingExpression)
     * @param action the action to be performed
     * @return a new expression where the current object in focus is the same
     */
    public final WhenBehaviorExpression<T> when(final ActionExpression<? super T> action) {
        action.perform(obj);
        return new WhenBehaviorExpression<T>(obj, action);
    }

    /**
     * Verifies the current state of the current object, with a matcher. This corresponds to
     * an assertThat(currentObject.someValue(), matcher) expression.
     * <p>
     * Example:
     * <pre>
     *  given(new Integer(5))
     *  .then(the -&gt; the.intValue(), is(5))
     *  .then(it(), is(5));
     * </pre>
     * @param <S> the type of object returned by the action
     * @param it a function on the current object
     * @param is the matcher
     * @return the behavior expression
     */
    public final <S> BehaviorExpression<T> then(final Function<? super T, S> it, final Matcher<? super S> is) {
        S result = it.apply(obj);
        assertThat(result, is);
        return new BasicBehaviorExpression<T>(obj);
    }
}
