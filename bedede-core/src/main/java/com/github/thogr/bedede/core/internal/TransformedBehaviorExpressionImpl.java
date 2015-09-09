package com.github.thogr.bedede.core.internal;

import static org.junit.Assert.assertThat;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;

import com.github.thogr.bedede.CoreExpressions;
import com.github.thogr.bedede.core.Behavior;
import com.github.thogr.bedede.core.Performing;
import com.github.thogr.bedede.core.Then;
import com.github.thogr.bedede.core.ThenMatches;
import com.github.thogr.bedede.core.Transforming;
import com.github.thogr.bedede.core.WhenPerforming;
import com.github.thogr.bedede.core.WhenTransforming;
import com.github.thogr.bedede.mocks.Mocked;

class TransformedBehaviorExpressionImpl<T, S> implements WhenTransforming<T, S>, ThenMatches<T, S> {

    private final T obj;
    private final S result;
    private final BasicBehaviorExpressionImpl<S> impl;

    TransformedBehaviorExpressionImpl(final T obj, final S result) {
        this.obj = obj;
        this.result = result;
        impl = new BasicBehaviorExpressionImpl<>(result);
    }

    @Override
    public ThenMatches<T, S> then(
            final BiFunction<? super T, ? super S, Matcher<? super S>> expr) {
        final Matcher<? super S> is = expr.apply(obj, result);
        assertThat(result, is);
        return this;
    }

    @Override
    public <S2> WhenTransforming<S, S2> when(
            final Transforming<? super S, ? extends S2> expr) {
        return impl.when(expr);
    }

    @Override
    public WhenPerforming<S> when(final Performing<S> expr) {
        return impl.when(expr);
    }

    @Override
    public <S2> Then<S> then(final Function<? super S, ? extends S2> it,
            final Matcher<? super S2> is) {
        return impl.then(it, is);
    }

    @Override
    public Then<S> then(final Predicate<? super S> predicate) {
        return impl.then(predicate);
    }

    @Override
    public <V> V then(final Mocked<V> mocked) {
        return CoreExpressions.then(mocked);
    }

    @Override
    public Then<Boolean> then(final boolean expr) {
        return CoreExpressions.then(expr);
    }

    @Override
    public Then<S> then(final Behavior<S> behavior) {
        return impl.then(behavior);
    }
}
