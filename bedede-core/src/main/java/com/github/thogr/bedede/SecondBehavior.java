package com.github.thogr.bedede;

import java.util.function.BiFunction;

import org.hamcrest.Matcher;

public interface SecondBehavior<T1, T2> extends Behavior<T1> {

    <S> SecondBehavior<T1, T2> then(
            BiFunction<? super T1, ? super T2, S> it, Matcher<? super S> is);
}
