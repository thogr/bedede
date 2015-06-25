package com.github.thogr.bedede;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;

abstract class BehaviorImpl<T> implements Behavior<T> {

    protected T obj;

    BehaviorImpl(T obj) {
        this.obj = obj;
    }

    BehaviorImpl(Behavior<T> expr) {
        BehaviorImpl<T> other = (BehaviorImpl<T>) expr;
        obj = other.obj;
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.Behavior#then(java.util.function.Function, org.hamcrest.Matcher)
     */
    @Override
    public final <S> Behavior<T> then(
            final Function<? super T, ? extends S> it, final Matcher<? super S> is) {
        S result = it.apply(obj);
        assertThat(result, is);
        return new BasicBehaviorExpressionImpl<T>(obj);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.Behavior#then(java.util.function.Predicate)
     */
    @Override
    public final Behavior<T> then(final Predicate<? super T> predicate) {
        boolean result = predicate.test(obj);
        assertThat(result, is(true));
        return new BasicBehaviorExpressionImpl<T>(obj);
    }
}
