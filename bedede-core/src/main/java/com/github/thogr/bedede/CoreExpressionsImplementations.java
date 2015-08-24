package com.github.thogr.bedede;

import static com.github.thogr.bedede.Defining.building;
import static org.hamcrest.CoreMatchers.is;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;
import org.junit.Assert;

import com.github.thogr.bedede.Defining.DefiningEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.mocks.Mocked;
import com.github.thogr.bedede.mocks.That;

abstract class CoreExpressionsImplementations {

    static <E> GivenElement<E> given(final Expecting<?> precondition) {
        return GivenElement.<E>given(precondition);
    }

    static <T> GivenBehaviorExpression<T> given(final AnObject<T> anObject) {
        return new GivenBehaviorExpressionImpl<T>(anObject.getWrapped());
    }

    static <T> GivenBehaviorExpression<T> given(final BehaviorExpression<T> expr) {
        return new GivenBehaviorExpressionImpl<T>(expr);
    }

    static <T> GivenBehaviorExpression<T> given(final Behavior<T> expr) {
        return new GivenBehaviorExpressionImpl<T>(expr);
    }

    static <T> PerformingExpression<T> performing(final ActionExpression<T> expr) {
        return new PerformingExpression<T>(expr);
    }

    static <T1, T2> BiPerformingExpression<T1, T2>
        performing(final BiActionExpression<T1, T2> expr) {
        return new BiPerformingExpression<T1, T2>(expr);
    }

    static <T> Function<T, T> it() {
        return (it -> it);
    }

    static <T, S> Function<T, S> the(Function<T, S> it) {
        return it;
    }

    static <T> Predicate<T> the(Predicate<T> it) {
        return it;
    }

    static <T1, T2, S> BiFunction<T1, T2, S> the(BiFunction<T1, T2, S> it) {
        return it;
    }

    static <T,S> TransformingExpression<T, S> retrieving(Function<T, S> expr) {
        return transforming(expr);
    }

    static <T,S> TransformingExpression<T, S> transforming(Function<T, S> expr) {
        return new TransformingExpression<>(expr);
    }

    static <T1,T2,S> BiTransformingExpression<T1, T2, S> retrieving(BiFunction<T1, T2, S> expr) {
        return new BiTransformingExpression<>(expr);
    }

    static <T1,T2,S> BiTransformingExpression<T1, T2, S> transforming(BiFunction<T1, T2, S> expr) {
        return new BiTransformingExpression<>(expr);
    }

    static <T> Behavior<T> then(Behavior<T> behavior) {
        return behavior;
    }

    static <T> Behavior<T> then(T it, Matcher<? super T> is) {
        Assert.assertThat(it, is);
        return new BasicBehaviorExpressionImpl<>(it);
    }

    static Behavior<Boolean> then(Boolean expr) {
        Assert.assertThat(expr, is(true));
        return new BasicBehaviorExpressionImpl<>(expr);
    }

    static <T> T given(That<T> mocked) {
        return mocked.getWrapped();
    }

    static <S> S then(Mocked<S> mocked) {
        return mocked.getWrapped();
    }

    static <T> AnObject<T> a(T object) {
        return new AnObject<>(object);
    }

    static <T> AnObject<T> an(T object) {
        return a(object);
    }

    static <T> Expecting<T> expecting(
            final T condition, final Otherwise otherwise) {
        @SuppressWarnings("unchecked")
        final Class<T> conditionClass = (Class<T>) condition.getClass();
        return Expecting.expecting(condition, conditionClass, otherwise);
    }

    static Expecting<BooleanCondition> expecting(
            final Boolean condition, final Otherwise otherwise) {
        return Expecting.expecting(() -> condition, BooleanCondition.class, otherwise);
    }

    static <T> DefiningEntry<T> entry(final Class<T> state) {
        return building().entry(state);
    }

}
