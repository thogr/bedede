package com.github.thogr.bedede.core;

import java.util.function.BiFunction;

import org.hamcrest.Matcher;

public interface ContinuedBehavior<F, T1, T2> extends Behavior<F> {

    /**
     * Verifies a matcher against the result of a {@link BiFunction}
     * @param it the object in focus
     * @param is the matcher
     * @param <S> the return type of the {@link BiFunction}
     * @return the continued specification with more verifications
     */
    <S> ThenItMatches<F, T1, T2> then(
            BiFunction<? super T1, ? super T2, S> it, Matcher<? super S> is);

    ThenItMatches<F, T1, T2> then(
            BiFunction<? super T1, ? super T2, Boolean> pred);
}
