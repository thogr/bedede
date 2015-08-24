package com.github.thogr.bedede;

import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;

import com.github.thogr.bedede.mocks.Mocked;

public interface Behavior<T> {

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
    <S> Behavior<T> then(Function<? super T, ? extends S> it, Matcher<? super S> is);

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
    Behavior<T> then(Predicate<? super T> predicate);

    Behavior<T> then(Behavior<T> behavior);

    <S> S then(Mocked<S> mocked);

    Behavior<Boolean> then(boolean expr);
}
