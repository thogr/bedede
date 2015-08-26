package com.github.thogr.bedede;

import java.util.function.BiFunction;

import org.hamcrest.Matcher;

public interface TransformedBehavior<T, S> extends Behavior<S> {
    ThenMatches<T, S> then(BiFunction<? super T, ? super S, Matcher<? super S>> expr);
}
