package com.github.thogr.bedede;

import static com.github.thogr.bedede.Defining.building;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

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
     * Sets the starting point for the further actions. If the state is
     * not the current assumed state it will possibly perform the actions needed to get
     * there, if the target state class has a declared default Entry.
     * The default Entry must be a public static field of type Entry&lt;T&gt; in the target
     * class, where T is the target class type.
     * If the class has more than one such field, one (and only one) must be annotated
     * with &#64;DefaultEntry.
     * @see Entry
     * @param <T> the type of state
     * @param target starting point for the coming actions
     * @return an Assuming which has methods to further actions.
     */
    public static <T> Assuming<T> given(final Class<T> state) {
        BehaviorDriver driver = new BehaviorDriver();
        return driver.given(state);
    }

    /**
     * Sets the starting point for the further actions. If the state is
     * not the current assumed state it will perform the actions needed to get
     * there, as specified by the entry.
     * @param entry the entry that should be performed
     * @param <T> the type of state
     * @return an Assuming which has methods to further actions
     */
    public static final <T> Assuming<T> given(final Entry<T> entry) {
        BehaviorDriver driver = new BehaviorDriver();
        return driver.given(entry);
    }

    /**
     * Sets the starting environment for a state-less test, for a more traditional unit test
     * but with behavior driven syntax - given().when()...then();
     * The starting environment is any object, which further when() and then() expressions will
     * operate on.
     * @param <T> the type of object or the starting environment
     * @param value initial value
     * @return the continued behavior expression
     */
    public static <T> BehaviorExpression<T> given(final T value) {
        return Expressions.given(value);
    }

    /**
     * Sets the starting environment for a state-less test, for a more traditional unit test
     * but with behavior driven syntax - given().when()...then();
     * In this case the starting environment is represented by a behavior expression, typically
     * returned by a method (perhaps extracted by a previous refactoring).
     * @param expr initial value
     * @param <T> the type of object the action is operating on
     * @return the continued behavior expression
     */
    public static <T> BehaviorExpression<T> given(final BehaviorExpression<T> expr) {
        return Expressions.given(expr);
    }

    /**
     * Wraps an action into a performing expression.
     * @see BehaviorExpression#when(PerformingExpression)
     * @param expr the action
     * @param <T> the type of object the action is operating on
     * @return the wrapped action
     */
    public static <T> PerformingExpression<T> performing(final ActionExpression<T> expr) {
        return Expressions.performing(expr);
    }

    public static <T1, T2> BiPerformingExpression<T1, T2> performing(final BiActionExpression<T1, T2> expr) {
        return Expressions.performing(expr);
    }

    /**
     * The identify function.
     * The same as writing  a lambda expression like <code>(it -&gt; it) </code>
     * This is useful in <code>then()</code> expressions.
     * <p>
     * Example:
     * </p>
     * <pre>
     *   given(new Integer(5))
     *   .then(it(), is(5));
     * </pre>
     * @param <T> the type of object the action is operating on
     * @return the identify function
     */
    public static <T> Function<T, T> it() {
        return Expressions.it();
    }

    /**
     * Wraps an action into a transforming expression.This is an alias for
     * {@link #transforming(Function)}, but with a name that reads better in some situations.
     * @see BehaviorExpression#when(TransformingExpression)
     * @param expr the action
     * @param <T> the type of object the action is operating on
     * @param <S> the type of object the next expression will be operating on
     * @return the wrapped action
     */
    public static <T,S> TransformingExpression<T, S> retrieving(Function<T, S> expr) {
        return Expressions.retrieving(expr);
    }

    /**
     * Wraps an action into a transforming expression.
     * @see BehaviorExpression#when(TransformingExpression)
     * @param expr the action
     * @param <T> the type of object the action is operating on
     * @param <S> the type of object the next expression will be operating on
     * @return the wrapped action
     */
    public static <T,S> TransformingExpression<T, S> transforming(Function<T, S> expr) {
        return Expressions.transforming(expr);
    }

    /**
     * Use carefully (alias for {@link #it(String)})
     * @param <T> the type of object the function is operating on
     * @param functionName
     * @return the function
     */
    public static <T> Function<T, Object> the(String functionName) {
        return Expressions.the(functionName);
    }

    public static <T> Function<T, Object> the(Function<T, Object> it) {
        return Expressions.the(it);
    }

    public static <T> Predicate<T> the(Predicate<T> it) {
        return Expressions.the(it);
    }

    public static <T1, T2, S> BiFunction<T1, T2, S> the(BiFunction<T1, T2, S> it) {
        return Expressions.the(it);
    }

    /**
     * Use carefully
     * @param <T> the type of object the action is operating on
     * @param functionName
     * @return the action
     */
    public static <T> ActionExpression<T> theAction(String actionName) {
        return Expressions.theAction(actionName);
    }

    /**
     * Use carefully (alias for {@link #the(String)})
     * @param <T> the type of object the action is operating on
     * @param functionName
     * @return the function
     */
    public static <T> Function<T, Object> it(String functionName) {
        return Expressions.the(functionName);
    }
}
