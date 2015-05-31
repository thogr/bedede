package com.github.thogr.bedede;

import static com.github.thogr.bedede.Defining.building;

import java.util.function.Function;

import com.github.thogr.bedede.Defining.DefiningEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;

public abstract class Bedede {

    protected Bedede(final Framework only) {
        if (only == null) {
            throw new NullPointerException();
        }
    }

    public static Expecting<BooleanCondition> expecting(
            final Boolean condition, final Otherwise otherwise) {
        return Expecting.expecting(() -> condition, BooleanCondition.class, otherwise);
    }

    public static <T> Expecting<T> expecting(
            final T condition, final Otherwise otherwise) {
        @SuppressWarnings("unchecked")
        final Class<T> conditionClass = (Class<T>) condition.getClass();
        return Expecting.expecting(condition, conditionClass, otherwise);
    }

    public static Otherwise otherwise(final String message) {
        return Otherwise.otherwise(message);
    }

    public static <T> DefiningEntry<T> entry(final Class<T> state) {
        return building().entry(state);
    }

    protected static class Internal {
        public static <E> GivenElement<E> given(final Expecting<?> expecting) {
            return GivenElement.<E>given(expecting);
        }
    }

    public static <T> BehaviorExpression<T> given(final T obj) {
        return Expressions.given(obj);
    }

    public static <T> NonFunctional<T> performing(final ActionExpression<T> expr) {
        return Expressions.performing(expr);
    }

    public static <T> Function<T, T> it() {
        return Expressions.it();
    }

    public static <T,S> Functional<T, S> retrieving(Function<T, S> expr) {
        return Expressions.retrieving(expr);
    }

    public static <T,S> Functional<T, S> transforming(Function<T, S> expr) {
        return Expressions.transforming(expr);
    }
}
