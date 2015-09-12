package com.github.thogr.bedede.core;

import java.util.function.BiFunction;

import org.hamcrest.Matcher;

public interface TransformedBehavior<T, S> extends Behavior<S> {

    /**
     * Similar to
     * {@link com.github.thogr.bedede.core.Behavior#then(java.util.function.Function, Matcher)}
     * , but it takes a BiFunction that returns a org.hamcrest.Matcher.
     * This is useful if you have a matcher that needs both the original object in focus and
     * the transformed object, after a when(transforming(...)).
     *
     * @param expr the function or lambda expression
     * @return the behavior
     */
    ThenMatches<T, S> then(BiFunction<? super T, ? super S, Matcher<? super S>> expr);
}
