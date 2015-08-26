package com.github.thogr.bedede.core.internal;

import static com.github.thogr.bedede.core.internal.Wrapped.getWrapped;
import static org.hamcrest.CoreMatchers.is;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;
import org.junit.Assert;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.AnObject;
import com.github.thogr.bedede.Assuming;
import com.github.thogr.bedede.Behavior;
import com.github.thogr.bedede.BehaviorExpression;
import com.github.thogr.bedede.BiActionExpression;
import com.github.thogr.bedede.Entry;
import com.github.thogr.bedede.Given;
import com.github.thogr.bedede.GivenElement;
import com.github.thogr.bedede.GivenPrefix;
import com.github.thogr.bedede.Otherwise;
import com.github.thogr.bedede.Performing;
import com.github.thogr.bedede.Transforming;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.core.CoreExpressions;
import com.github.thogr.bedede.core.internal.Defining.DefiningEntry;
import com.github.thogr.bedede.mocks.Mocked;
import com.github.thogr.bedede.mocks.That;

public final class CoreExpressionsImpl {

    public CoreExpressionsImpl(CoreExpressions core) {
        if (core == null) {
            throw new NullPointerException();
        }
    }

    public Otherwise otherwise(final String message) {
        return Otherwise.otherwise(message);
    }

    public static <E> GivenElement<E> given(final Expecting<?> precondition) {
        return GivenElementImpl.<E>given(precondition);
    }

    public GivenPrefix given() {
        return new GivenPrefixImpl();
    }

    public <T> Assuming<T> given(final Class<T> state) {
        BehaviorDriver driver = new BehaviorDriver();
        return driver.given(state);
    }

    public final <T> Assuming<T> given(final Entry<T> entry) {
        BehaviorDriver driver = new BehaviorDriver();
        return driver.given(entry);
    }

    public <T> Given<T> given(final Wrapped<T> anObject) {
        return new GivenBehaviorExpressionImpl<T>(anObject.getWrapped());
    }

    public <T> Given<T> given(final BehaviorExpression<T> expr) {
        return new GivenBehaviorExpressionImpl<T>(expr);
    }

    public <T> Given<T> given(final Behavior<T> expr) {
        return new GivenBehaviorExpressionImpl<T>(expr);
    }

    public <T> Performing<T> performing(final ActionExpression<T> expr) {
        return new PerformingImpl<T>(expr);
    }

    public <T1, T2> BiPerformingImpl<T1, T2>
        performing(final BiActionExpression<T1, T2> expr) {
        return new BiPerformingImpl<T1, T2>(expr);
    }

    public <T> Function<T, T> it() {
        return (it -> it);
    }

    public <T, S> Function<T, S> the(Function<T, S> it) {
        return it;
    }

    public <T> Predicate<T> the(Predicate<T> it) {
        return it;
    }

    public <T1, T2, S> BiFunction<T1, T2, S> the(BiFunction<T1, T2, S> it) {
        return it;
    }

    public <T,S> Transforming<T, S> retrieving(Function<T, S> expr) {
        return transforming(expr);
    }

    public <T,S> Transforming<T, S> transforming(Function<T, S> expr) {
        return new TransformingImpl<>(expr);
    }

    public <T1,T2,S> BiTransformingImpl<T1, T2, S> retrieving(BiFunction<T1, T2, S> expr) {
        return new BiTransformingImpl<>(expr);
    }

    public <T1,T2,S> BiTransformingImpl<T1, T2, S> transforming(BiFunction<T1, T2, S> expr) {
        return new BiTransformingImpl<>(expr);
    }

    public <T> Behavior<T> then(Behavior<T> behavior) {
        return behavior;
    }

    public <T> Behavior<T> then(T it, Matcher<? super T> is) {
        Assert.assertThat(it, is);
        return new BasicBehaviorExpressionImpl<>(it);
    }

    public Behavior<Boolean> then(Boolean expr) {
        Assert.assertThat(expr, is(true));
        return new BasicBehaviorExpressionImpl<>(expr);
    }

    public <T> T given(That<T> mocked) {
        return getWrapped(mocked);
    }

    public <S> S then(Mocked<S> mocked) {
        return getWrapped(mocked);
    }

    public <T> AnObject<T> a(T object) {
        return AnObject.a(object);
    }

    public <T> AnObject<T> an(T object) {
        return a(object);
    }

    public static <T> Expecting<T> expecting(
            final T condition, final Otherwise otherwise) {
        @SuppressWarnings("unchecked")
        final Class<T> conditionClass = (Class<T>) condition.getClass();
        return Expecting.expecting(condition, conditionClass, otherwise);
    }

    public Expecting<BooleanCondition> expecting(
            final Boolean condition, final Otherwise otherwise) {
        return Expecting.expecting(() -> condition, BooleanCondition.class, otherwise);
    }

    public <T> DefiningEntry<T> entry(final Class<T> state) {
        return Defining.building().entry(state);
    }

}
