package com.github.thogr.bedede.core;

import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;

import com.github.thogr.bedede.core.internal.Proxy;
import com.github.thogr.bedede.mocks.Mocked;

public interface Behavior<T> {

    /**
     * Verifies the current state of the current object, with a matcher. This corresponds to
     * an assertThat(currentObject.someValue(), matcher) expression.
     * <p>
     * Example:
     * <pre>
     *  given(a(new Integer(5)))
     *  .then(the -&gt; the.intValue(), is(5))
     *  .then(it(), is(5));
     * </pre>
     * @param <S> the type of object returned by the action
     * @param it a function on the current object
     * @param is the matcher
     * @return the behavior
     */
    <S> Then<T> then(Function<? super T, ? extends S> it, Matcher<? super S> is);

    /**
     * Verifies the current state of the current object, with a predicate. This corresponds to
     * an assertThat(currentObject.predicate(), is(true)) expression.
     * <p>
     * Example:
     * <pre>
     *  given(a(""))
     *  .then(it -&gt; it.isEmpty());
     * </pre>
     * @param predicate a predicate function on the current object
     * @return the behavior expression
     */
    Then<T> then(Predicate<? super T> predicate);

    /**
     * See {@link com.github.thogr.bedede.CoreExpressions#then(Behavior)}
     */
    @Proxy
    Then<T> then(Behavior<T> behavior);

    /**
     * See {@link com.github.thogr.bedede.CoreExpressions#then(Mocked)}
     */
    @Proxy
    <S> S then(Mocked<S> mocked);

    /**
     * See {@link com.github.thogr.bedede.CoreExpressions#then(boolean)}
     */
    @Proxy
    Then<Boolean> then(boolean expr);
}
