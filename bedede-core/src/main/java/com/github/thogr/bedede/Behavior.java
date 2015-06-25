package com.github.thogr.bedede;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;

public abstract class Behavior<T> {

    protected T obj;

    Behavior(T obj) {
        this.obj = obj;
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
    public final <S> Behavior<T> then(
            final Function<? super T, ? extends S> it, final Matcher<? super S> is) {
        S result = it.apply(obj);
        assertThat(result, is);
        return new BasicBehaviorExpression<T>(obj);
    }

    /**
    * Verifies the current state of the current object, with a predicate. This corresponds to
    * an assertThat(currentObject.predicate(), is(true)) expression.
    * <p>
    * Example:
    * <pre>
    *  given("")
    *  .then(it -&gt; it.isEmpty());
    * </pre>
    * @param predicate a predicate function on the current object
    * @return the behavior expression
    */
    public final Behavior<T> then(final Predicate<? super T> predicate) {
        boolean result = predicate.test(obj);
        assertThat(result, is(true));
        return new BasicBehaviorExpression<T>(obj);
    }
}
