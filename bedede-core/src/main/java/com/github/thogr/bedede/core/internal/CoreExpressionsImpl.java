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
import com.github.thogr.bedede.Then;
import com.github.thogr.bedede.Transforming;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.core.CoreExpressions;
import com.github.thogr.bedede.core.internal.Defining.DefiningEntry;
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
    public Otherwise otherwise(final String message) {
        return Otherwise.otherwise(message);
    }

    @Internal
    public static <E> GivenElement<E> given(final Expecting<?> precondition) {
        return GivenElementImpl.<E>given(precondition);
    }

    @Internal
    public GivenPrefix given() {
        return new GivenPrefixImpl();
    }

    @Internal
    public <T> Assuming<T> given(final Class<T> state) {
        final BehaviorDriver driver = new BehaviorDriver();
        return driver.given(state);
    }

    @Internal
    public <T> Assuming<T> given(final Entry<T> entry) {
        final BehaviorDriver driver = new BehaviorDriver();
        return driver.given(entry);
    }

    @Internal
    public <T> Given<T> given(final Wrapped<T> anObject) {
        return new GivenBehaviorExpressionImpl<T>(anObject.getWrapped());
    }

    @Internal
    public <T> Given<T> given(final BehaviorExpression<T> expr) {
        return new GivenBehaviorExpressionImpl<T>(expr);
    }

    @Internal
    public <T> Given<T> given(final Behavior<T> expr) {
        return new GivenBehaviorExpressionImpl<T>(expr);
    }

    @Internal
    public <T> Performing<T> performing(final ActionExpression<T> expr) {
        return new PerformingImpl<T>(expr);
    }

    @Internal
    public <T1, T2> BiPerformingImpl<T1, T2>
        performing(final BiActionExpression<T1, T2> expr) {
        return new BiPerformingImpl<T1, T2>(expr);
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

    @Internal
    public static <T> Expecting<T> expecting(
            final T condition, final Otherwise otherwise) {
        @SuppressWarnings("unchecked")
        final Class<T> conditionClass = (Class<T>) condition.getClass();
        return Expecting.expecting(condition, conditionClass, otherwise);
    }

    @Internal
    public Expecting<BooleanCondition> expecting(
            final Boolean condition, final Otherwise otherwise) {
        return Expecting.expecting(() -> condition, BooleanCondition.class, otherwise);
    }

    @Internal
    public <T> DefiningEntry<T> entry(final Class<T> state) {
        return Defining.building().entry(state);
    }
}
