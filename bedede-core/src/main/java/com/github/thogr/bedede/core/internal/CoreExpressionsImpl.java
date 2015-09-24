//CHECKSTYLE:OFF FanOutComplexity
//CHECKSTYLE:OFF ClassDataAbstractionCoupling

package com.github.thogr.bedede.core.internal;

import static com.github.thogr.bedede.core.internal.Wrapped.getWrapped;
import static org.hamcrest.CoreMatchers.is;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;
import org.junit.Assert;

import com.github.thogr.bedede.CoreExpressions;
import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.core.AnObject;
import com.github.thogr.bedede.core.Behavior;
import com.github.thogr.bedede.core.BiActionExpression;
import com.github.thogr.bedede.core.Given;
import com.github.thogr.bedede.core.Performing;
import com.github.thogr.bedede.core.Then;
import com.github.thogr.bedede.core.Transforming;
import com.github.thogr.bedede.core.WhenPerforming;
import com.github.thogr.bedede.core.WhenTransforming;
import com.github.thogr.bedede.mocks.Mocked;
import com.github.thogr.bedede.mocks.That;

@Internal
public final class CoreExpressionsImpl {

    private static final String WHEN_OUTSIDE_GIVEN =
            "Error: Method "
            + "when"
            + "() called outside of given(). " +
            "It may only be called nested, like: " +
            "given(a(), -> when())";

    @Internal
    public CoreExpressionsImpl(final CoreExpressions core) {
        if (core == null) {
            throw new NullPointerException();
        }
        current.set(new Stack<>());
    }

    private final ThreadLocal<Stack<Object>> current = new ThreadLocal<>();

    @Internal
    @SuppressWarnings("unchecked")
    public <T> WhenPerforming<T> whenPerforming(final Performing<T> performing) {
        try {
            return new GivenBehaviorExpressionImpl<>((T) current.get().peek()).when(performing);
        } catch (final EmptyStackException e) {
            throw new IllegalArgumentException(WHEN_OUTSIDE_GIVEN, e);
        }
    }

    @Internal
    @SuppressWarnings("unchecked")
    public <T, S> WhenTransforming<T, S> whenTransforming(
            final Transforming<? super T, ? extends S> transforming) {
        try {
            return new GivenBehaviorExpressionImpl<>((T) current.get().peek()).when(transforming);
        } catch (final EmptyStackException e) {
            throw new IllegalArgumentException(WHEN_OUTSIDE_GIVEN, e);
        }
    }

    @Internal
    public <T> Given<T> givenAnObject(final Wrapped<T> anObject) {
        return new GivenBehaviorExpressionImpl<>(anObject.getWrapped());
    }

    @SuppressWarnings("unchecked")
    @Internal
    public <T,S> Behavior<S> givenNested(final Wrapped<T> anObject, final Function<T, Behavior<? extends S>> nested) {
        current.get().push(anObject.getWrapped());
        final Behavior<S> inner = (Behavior<S>) nested.apply(anObject.getWrapped());
        current.get().pop();
        return inner;
    }

    @Internal
    public <T> Given<T> givenBehavior(final Behavior<T> expr) {
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
    public <T> Then<T> thenBehavior(final Behavior<T> behavior) {
        return new BasicBehaviorExpressionImpl<>(behavior);
    }

    @Internal
    public <T> Then<T> thenItMatches(final T it, final Matcher<? super T> is) {
        Assert.assertThat(it, is);
        return new BasicBehaviorExpressionImpl<>(it);
    }

    @Internal
    public Then<Boolean> thenTrue(final Boolean expr) {
        Assert.assertThat(expr, is(true));
        return new BasicBehaviorExpressionImpl<>(expr);
    }

    @Internal
    public <T> T givenThat(final That<T> mocked) {
        return getWrapped(mocked);
    }

    @Internal
    public <S> S thenMocked(final Mocked<S> mocked) {
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
