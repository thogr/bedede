package com.github.thogr.bedede.core.internal;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;

import com.github.thogr.bedede.Behavior;
import com.github.thogr.bedede.Then;
import com.github.thogr.bedede.core.CoreExpressions;
import com.github.thogr.bedede.mocks.Mocked;

abstract class BehaviorImpl<T> implements Behavior<T>, Then<T> {

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
    public final <S> Then<T> then(
            final Function<? super T, ? extends S> it, final Matcher<? super S> is) {
        S result = it.apply(obj);
        assertThat(result, is);
        return new BasicBehaviorExpressionImpl<T>(obj);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.Behavior#then(java.util.function.Predicate)
     */
    @Override
    public final Then<T> then(final Predicate<? super T> predicate) {
        boolean result = predicate.test(obj);
        assertThat(result, is(true));
        return new BasicBehaviorExpressionImpl<T>(obj);
    }

    @Override
    public Then<T> then(Behavior<T> behavior) {
        return CoreExpressions.then(behavior);
    }

    @Override
    public <S> S then(Mocked<S> mocked) {
        return CoreExpressions.then(mocked);
    }

    @Override
    public Then<Boolean> then(boolean expr) {
        return CoreExpressions.then(expr);
    }
}
