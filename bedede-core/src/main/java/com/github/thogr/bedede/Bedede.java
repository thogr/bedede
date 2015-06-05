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

    /**
     * For future use. In the future this may be possible, but for now your test will need to
     * extend BehaviorDriven, to be able to call given() on a state (i.e. a class)
     * @deprecated for now - use {@link BehaviorDriven#given(Class)}
     */
    public static <T> BehaviorExpression<T> given(final Class<T> clazz) {
        throw new IllegalArgumentException(
                "Use " + BehaviorDriven.class.getName() + ".given(Class state)");
    }

    /**
     * Sets the starting environment for a state-less test, for a more traditional unit test
     * but with behavior driven syntax - given().when()...then();
     * The starting environment is any object, which further when() and then() expressions will
     * operate on.
     * @param obj initial value
     * @return the continued behavior expression
     */
    public static <T> BehaviorExpression<T> given(final T obj) {
        return Expressions.given(obj);
    }

    /**
     * Sets the starting environment for a state-less test, for a more traditional unit test
     * but with behavior driven syntax - given().when()...then();
     * In this case the starting environment is represented by a behavior expression, typically
     * returned by a method (perhaps extracted by a previous refactoring).
     * @param obj initial value
     * @return the continued behavior expression
     */
    public static <T> BehaviorExpression<T> given(final BehaviorExpression<T> expr) {
        return Expressions.given(expr);
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
