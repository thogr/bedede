package com.github.thogr.bedede.core.internal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;

import com.github.thogr.bedede.CoreExpressions;
import com.github.thogr.bedede.core.Behavior;
import com.github.thogr.bedede.core.Then;
import com.github.thogr.bedede.mocks.Mocked;

abstract class BehaviorImpl<T> implements Behavior<T>, Then<T> {

    private final T obj;

    BehaviorImpl(final T obj) {
        this.obj = obj;
    }

    BehaviorImpl(final Behavior<T> expr) {
        final BehaviorImpl<T> other = (BehaviorImpl<T>) expr;
        obj = other.obj;
    }

    protected T getFocusedObject() {
        return obj;
    }

    @Override
    public final <S> Then<T> then(
            final Function<? super T, ? extends S> it, final Matcher<? super S> is) {
        return thenItMatches(it, is);
    }

    <S> Then<T> thenItMatches(
            final Function<? super T, ? extends S> it, final Matcher<? super S> is) {
        final S result = it.apply(obj);
        assertThat(result, is);
        return new BasicBehaviorExpressionImpl<T>(obj);
    }

    @Override
    public final Then<T> then(final Predicate<? super T> predicate) {
        final boolean result = predicate.test(obj);
        assertThat(result, is(true));
        return new BasicBehaviorExpressionImpl<T>(obj);
    }

    @Override
    public Then<T> then(final Behavior<T> behavior) {
        return CoreExpressions.then(behavior);
    }

    @Override
    public <S> S then(final Mocked<S> mocked) {
        return CoreExpressions.then(mocked);
    }

    @Override
    public Then<Boolean> then(final boolean expr) {
        return CoreExpressions.then(expr);
    }
}
