// CHECKSTYLE:OFF FileLength
// CHECKSTYLE:OFF FanOutComplexity

package com.github.thogr.bedede;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;

import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.core.AnObject;
import com.github.thogr.bedede.core.Behavior;
import com.github.thogr.bedede.core.BiActionExpression;
import com.github.thogr.bedede.core.BiPerforming;
import com.github.thogr.bedede.core.BiTransforming;
import com.github.thogr.bedede.core.Given;
import com.github.thogr.bedede.core.Performing;
import com.github.thogr.bedede.core.Then;
import com.github.thogr.bedede.core.Transforming;
import com.github.thogr.bedede.core.internal.CoreExpressionsImpl;
import com.github.thogr.bedede.mocks.Mocked;
import com.github.thogr.bedede.mocks.That;

public class CoreExpressions {

    private static CoreExpressionsImpl impl =
            new CoreExpressionsImpl(new CoreExpressions());

    protected CoreExpressions() {

    }

    /**
     * Sets the starting environment for a state-less test, for a more traditional unit test
     * but with behavior driven syntax - given().when()...then();
     * The starting environment is the object in focus, which further when() and then() expressions
     * will operate on.
     * <p><b>Syntax:</b></p>
     * given(a(new Something()))
     * </p>
     * @param <T> the type of object (in focus) or the starting environment
     * @param anObject the initial object in focus expressed as a(object)
     * @return the continued behavior expression
     */
    public static <T> Given<T> given(final AnObject<T> anObject) {
        return impl.given(anObject);
    }

    /**
     * Sets the starting environment for a state-less test, for a more traditional unit test
     * but with behavior driven syntax - given().when()...then();
     * In this case the starting environment is represented by a behavior expression, typically
     * returned by a method (perhaps extracted by a previous refactoring).
     * @param expr initial value
     * @param <T> the type of object (in focus) the action is operating on
     * @return the continued behavior expression
     */
    public static <T> Given<T> given(final Behavior<T> expr) {
        return impl.given(expr);
    }

    /**
     * Sets the starting environment for a test using mocks. This method merely calls the mocking
     * framework, but is needed because of possible name conflicts in the mock framework.
     * @param that a wrapped mocked object
     * @param <T> the type of the mock
     * @return the mock
     */
    public static <T> T given(final That<T> that) {
        return impl.given(that);
    }

    /**
     * Wraps an action that operates on an object into a performing expression.
     * The object originates from a given(object) expression.
     * @see com.github.thogr.bedede.core.BehaviorExpression#when(PerformingImpl)
     * @param expr the action
     * @param <T> the type of object (in focus) the action is operating on
     * @return the wrapped action
     */
    public static <T> Performing<T> performing(final ActionExpression<T> expr) {
        return impl.performingAction(expr);
    }

    /**
     * Wraps an action that operates on two objects into a performing expression.
     * The objects originates from a given(object1).given(object2)
     * or given(object1).and(object2) expression.
     * @see com.github.thogr.bedede.core.BehaviorExpression#when(PerformingImpl)
     * @param expr the action
     * @param <T1> the type of the first object (in focus) the action operates on
     * @param <T2> the type of the second object (in focus) the action operates on
     * @return the wrapped action
     */
    public static <T1, T2> BiPerforming<T1, T2> performing(
            final BiActionExpression<T1, T2> expr) {
        return impl.performingBiAction(expr);
    }

    /**
     * The identify function.
     * The same as writing  a lambda expression like <code>(it -&gt; it) </code>
     * This is useful in <code>then()</code> expressions.
     * <p>
     * Example:
     * </p>
     * <pre>
     *   given(a(new Integer(5)))
     *   .then(it(), is(5));
     * </pre>
     * @param <T> the type of object (in focus) the action is operating on
     * @return the identify function
     */
    public static <T> Function<T, T> it() {
        return impl.it();
    }

    /**
     * Wraps an action into a transforming expression.This is an alias for
     * {@link #transforming(Function)}, but with a name that reads better in some situations.
     * @see com.github.thogr.bedede.core.BehaviorExpression#when(Transforming)
     * @param expr the action
     * @param <T> the type of object (in focus) the action is operating on
     * @param <S> the type of object the next expression will be operating on (next in focus)
     * @return the wrapped action
     */
    public static <T, S> Transforming<T, S> retrieving(final Function<T, S> expr) {
        return impl.retrieving(expr);
    }

    /**
     * Wraps an action into a transforming expression.
     * @see com.github.thogr.bedede.core.BehaviorExpression#when(Transforming)
     * @param expr the action
     * @param <T> the type of object (in focus) the action is operating on
     * @param <S> the type of object the next expression will be operating on (next in focus)
     * @return the wrapped action
     */
    public static <T, S> Transforming<T, S> transforming(final Function<T, S> expr) {
        return impl.transforming(expr);
    }

    /**
     * Wraps an action that operates on two objects into a transforming expression.
     * This is an alias for {@link #transforming(BiFunction)}, but with a name that reads
     * better in some situations.
     * @see com.github.thogr.bedede.core.ContinuedBehaviorExpression#when(BiTransformingImpl)
     * @param expr the action
     * @param <T1> the type of the first object (in focus) the action operates on
     * @param <T2> the type of the second object (in focus) the action operates on
     * @param <S> the type of object the next expression will be operating on (next in focus)
     * @return the wrapped action
     */
    public static <T1, T2, S> BiTransforming<T1, T2, S>
        retrieving(final BiFunction<T1, T2, S> expr) {
        return impl.retrieving(expr);
    }

    /**
     * Wraps an action that operates on two objects into a transforming expression.
     * This is an alias for {@link #retrieving(BiFunction)}, but with a name that reads
     * better in some situations.
     * @see com.github.thogr.bedede.core.ContinuedBehaviorExpression#when(BiTransformingImpl)
     * @param expr the action
     * @param <T1> the type of the first object (in focus) the action operates on
     * @param <T2> the type of the second object (in focus) the action operates on
     * @param <S> the type of object the next expression will be operating on (next in focus)
     * @return the wrapped action
     */
    public static <T1, T2, S> BiTransforming<T1, T2, S>
        transforming(final BiFunction<T1, T2, S> expr) {
        return impl.transforming(expr);
    }

    /**
     * Decorates a {@link Function}, retaining its behavior, but allowing tests
     * to be slightly more expressive.
     * This is useful in <code>then()</code> and <code>when()</code> expressions with method
     * references, instead of lambda expressions.
     * @param it the function
     * @param <T> the type of the input to the function, i.e. the object in focus
     * @param <S> the type of the result of the function
     * @return the function
     */
    public static <T, S> Function<T, S> the(final Function<T, S> it) {
        return impl.the(it);
    }

    /**
     * Decorates a {@link Predicate}, retaining its behavior, but allowing tests
     * to be slightly more expressive.
     * This is useful in <code>then()</code> and <code>when()</code> expressions with method
     * references, instead of lambda expressions.
     * @param it the predicate
     * @param <T> the type of the input to the predicate, i.e. the object in focus
     * @return the predicate
     */
    public static <T> Predicate<T> the(final Predicate<T> it) {
        return impl.the(it);
    }

    /**
     * Decorates a {@link BiFunction}, retaining its behavior, but allowing tests
     * to be slightly more expressive.
     * This is useful in <code>then()</code> and <code>when()</code> expressions with method
     * references, instead of lambda expressions.
     * @param it the function
     * @param <T1> the type of the first object (in focus) the function operates on
     * @param <T2> the type of the second object (in focus) the function operates on
     * @param <S> the type of the result of the function
     * @return the function
     */
    public static <T1, T2, S> BiFunction<T1, T2, S> the(final BiFunction<T1, T2, S> it) {
        return impl.the(it);
    }

    /**
     * Wraps the object that will be in focus.
     * <p>Example:</p>
     * <pre>
     * given(a(new Something()))
     * </pre>
     * @param object any object
     * @param <T> the type of the object in focus
     * @return the wrapped object
     */
    public static <T> AnObject<T> a(final T object) {
        return impl.a(object);
    }

    /**
     * Alias for {@link CoreExpressions#a(Object)}
     * @param object any object
     * @param <T> the type of the object in focus
     * @return the wrapped object
     */
    public static <T> AnObject<T> an(final T object) {
        return impl.a(object);
    }

    /**
     * Alias for {@link org.junit.Assert#assertThat(Object, Matcher)} BDD style
     * @param <T> the type of object (in focus) the action is operating on,
     * the static type accepted by the matcher
     * @param it the value being matched
     * @param is the matcher
     * @return the behavior
     */
    public static <T> Then<T> then(final T it, final Matcher<? super T> is) {
        return impl.then(it, is);
    }

   /**
    * Convenience method corresponding to a call to {@link org.junit.Assert#assertTrue(boolean)}
    * @param expr boolean expression
    * @return the behavior
    * @see Behavior#then(boolean)
    */
    public static Then<Boolean> then(final boolean expr) {
        return impl.then(expr);
    }

    /**
     * Convenience method when a behavior has been extracted to a method to be reused in another
     * test.
     * @param behavior the other (reused) behavior
     * @param <T> the type of the object in focus
     * @return this behavior
     * @see Behavior#then(Behavior)
     */
    public static <T> Then<T> then(final Behavior<T> behavior) {
        return impl.then(behavior);
    }

    /**
     * Verify call to mock.
     * <p>Example:</p>
     * <pre>then(theMocked(mock)).should().someMethod()</pre>
     * This method merely calls the mocking
     * framework, but is needed because of possible name conflicts in the mock framework.
     * @param mocked the mocked object (wrapped)
     * @param <S> mocking framework dependent type
     * @return the framework dependent continuation
     * @see Behavior#then(Mocked)
     */
    public static <S> S then(final Mocked<S> mocked) {
        return impl.then(mocked);
    }
}
