//CHECKSTYLE:OFF FanOutComplexity
//CHECKSTYLE:OFF ClassDataAbstractionCoupling

package com.github.thogr.bedede.core.internal;

import static com.github.thogr.bedede.core.internal.Wrapped.getWrapped;
import static org.hamcrest.CoreMatchers.is;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;
import org.junit.Assert;

import com.github.thogr.bedede.CoreExpressions;
import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.core.AnObject;
import com.github.thogr.bedede.core.Behavior;
import com.github.thogr.bedede.core.BehaviorExpression;
import com.github.thogr.bedede.core.BiActionExpression;
import com.github.thogr.bedede.core.Given;
import com.github.thogr.bedede.core.Performing;
import com.github.thogr.bedede.core.Then;
import com.github.thogr.bedede.core.Transforming;
import com.github.thogr.bedede.mocks.Mocked;
import com.github.thogr.bedede.mocks.That;

@Internal
public final class CoreExpressionsImpl {

    @Internal
    public CoreExpressionsImpl(final CoreExpressions core) {
        if (core == null) {
            throw new NullPointerException();
        }
    }

    @Internal
    public <T> Given<T> given(final Wrapped<T> anObject) {
        return new GivenBehaviorExpressionImpl<>(anObject.getWrapped());
    }

    @SuppressWarnings("unchecked")
    @Internal
    public <T,S> Behavior<S> given(final Wrapped<T> anObject, final Function<T, Behavior<? extends S>> nested) {
        return (Behavior<S>) nested.apply(anObject.getWrapped());
    }

    @Internal
    public <T> Given<T> given(final BehaviorExpression<T> expr) {
        return new GivenBehaviorExpressionImpl<>(expr);
    }

    @Internal
    public <T> Given<T> given(final Behavior<T> expr) {
        return new GivenBehaviorExpressionImpl<>(expr);
    }

    @Internal
    public <T> Performing<T> performingAction(final ActionExpression<T> expr) {
        return new PerformingImpl<>(expr);
    }

    @Internal
    public <T1, T2> BiPerformingImpl<T1, T2>
        performingBiAction(final BiActionExpression<T1, T2> expr) {
        return new BiPerformingImpl<>(expr);
    }

    @Internal
    public <T> Function<T, T> it() {
        return (it -> it);
    }

    @Internal
    public <T, S> Function<T, S> the(final Function<T, S> it) {
        return it;
    }

    @Internal
    public <T> Predicate<T> the(final Predicate<T> it) {
        return it;
    }

    @Internal
    public <T1, T2, S> BiFunction<T1, T2, S> the(final BiFunction<T1, T2, S> it) {
        return it;
    }

    @Internal
    public <T, S> Transforming<T, S> retrieving(final Function<T, S> expr) {
        return transforming(expr);
    }

    @Internal
    public <T, S> Transforming<T, S> transforming(final Function<T, S> expr) {
        return new TransformingImpl<>(expr);
    }

    @Internal
    public <T1, T2, S> BiTransformingImpl<T1, T2, S> retrieving(final BiFunction<T1, T2, S> expr) {
        return new BiTransformingImpl<>(expr);
    }

    @Internal
    public <T1, T2, S> BiTransformingImpl<T1, T2, S> transforming(
            final BiFunction<T1, T2, S> expr) {
        return new BiTransformingImpl<>(expr);
    }

    @Internal
    public <T> Then<T> then(final Behavior<T> behavior) {
        return new BasicBehaviorExpressionImpl<>(behavior);
    }

    @Internal
    public <T> Then<T> then(final T it, final Matcher<? super T> is) {
        Assert.assertThat(it, is);
        return new BasicBehaviorExpressionImpl<>(it);
    }

    @Internal
    public Then<Boolean> then(final Boolean expr) {
        Assert.assertThat(expr, is(true));
        return new BasicBehaviorExpressionImpl<>(expr);
    }

    @Internal
    public <T> T given(final That<T> mocked) {
        return getWrapped(mocked);
    }

    @Internal
    public <S> S then(final Mocked<S> mocked) {
        return getWrapped(mocked);
    }

    @Internal
    public <T> AnObject<T> a(final T object) {
        return AnObject.a(object);
    }

    @Internal
    public <T> AnObject<T> an(final T object) {
        return a(object);
    }
}
