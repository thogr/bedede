package com.github.thogr.bedede;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.mockito.BDDMockito.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.Defining.DefiningEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.mocks.MockExpressions;
import com.github.thogr.bedede.mocks.Mocked;
import com.github.thogr.bedede.mocks.That;

public abstract class Expressions {

    // CoreExpressions
    //-----------------------------------------------------------------

    public static Expecting<BooleanCondition> expecting(
    final Boolean condition, final Otherwise otherwise) {
        return CoreExpressions.expecting(condition, otherwise);
    }

    public static Otherwise otherwise(final String message) {
        return CoreExpressions.otherwise(message);
    }

    public static <T> DefiningEntry<T> entry(final Class<T> state) {
        return CoreExpressions.entry(state);
    }

    public static GivenPrefix given() {
        return CoreExpressions.given();
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
     * @param state starting point for the coming actions
     * @return an Assuming which has methods to further actions.
     */
    public static <T> Assuming<T> given(final Class<T> state) {
        return CoreExpressions.given(state);
    }

    /**
     * Sets the starting point for the further actions. If the state is
     * not the current assumed state it will perform the actions needed to get
     * there, as specified by the entry.
     * @param entry the entry that should be performed
     * @param <T> the type of state
     * @return an Assuming which has methods to further actions
     */
    public static <T> Assuming<T> given(final Entry<T> entry) {
        return CoreExpressions.given(entry);
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
    public static <T> GivenBehaviorExpression<T> given(final AnObject<T> anObject) {
        return CoreExpressions.given(anObject);
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
    public static <T> GivenBehaviorExpression<T> given(final Behavior<T> expr) {
        return CoreExpressions.given(expr);
    }

    public static <T> T given(That<T> that) {
        return CoreExpressions.given(that);
    }

    /**
     * Wraps an action that operates on an object into a performing expression.
     * The object originates from a given(object) expression.
     * @see BehaviorExpression#when(PerformingExpression)
     * @param expr the action
     * @param <T> the type of object (in focus) the action is operating on
     * @return the wrapped action
     */
    public static <T> PerformingExpression<T> performing(final ActionExpression<T> expr) {
        return CoreExpressions.performing(expr);
    }

    /**
     * Wraps an action that operates on two objects into a performing expression.
     * The objects originates from a given(object1).given(object2)
     * or given(object1).and(object2) expression.
     * @see BehaviorExpression#when(PerformingExpression)
     * @param expr the action
     * @param <T1> the type of the first object (in focus) the action operates on
     * @param <T2> the type of the second object (in focus) the action operates on
     * @return the wrapped action
     */
    public static <T1, T2> BiPerformingExpression<T1, T2> performing(
            final BiActionExpression<T1, T2> expr) {
        return CoreExpressions.performing(expr);
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
     * @param <T> the type of object (in focus) the action is operating on
     * @return the identify function
     */
    public static <T> Function<T, T> it() {
        return CoreExpressions.it();
    }

    /**
     * Wraps an action into a transforming expression.This is an alias for
     * {@link #transforming(Function)}, but with a name that reads better in some situations.
     * @see BehaviorExpression#when(TransformingExpression)
     * @param expr the action
     * @param <T> the type of object (in focus) the action is operating on
     * @param <S> the type of object the next expression will be operating on (next in focus)
     * @return the wrapped action
     */
    public static <T,S> TransformingExpression<T, S> retrieving(Function<T, S> expr) {
        return CoreExpressions.retrieving(expr);
    }

    /**
     * Wraps an action into a transforming expression.
     * @see BehaviorExpression#when(TransformingExpression)
     * @param expr the action
     * @param <T> the type of object (in focus) the action is operating on
     * @param <S> the type of object the next expression will be operating on (next in focus)
     * @return the wrapped action
     */
    public static <T,S> TransformingExpression<T, S> transforming(Function<T, S> expr) {
        return CoreExpressions.transforming(expr);
    }

    /**
     * Wraps an action that operates on two objects into a transforming expression.
     * This is an alias for {@link #transforming(BiFunction)}, but with a name that reads
     * better in some situations.
     * @see ContinuedBehaviorExpression#when(BiTransformingExpression)
     * @param expr the action
     * @param <T1> the type of the first object (in focus) the action operates on
     * @param <T2> the type of the second object (in focus) the action operates on
     * @param <S> the type of object the next expression will be operating on (next in focus)
     * @return the wrapped action
     */
    public static <T1,T2,S> BiTransformingExpression<T1, T2, S>
        retrieving(BiFunction<T1, T2, S> expr) {
        return CoreExpressions.retrieving(expr);
    }

    /**
     * Wraps an action that operates on two objects into a transforming expression.
     * This is an alias for {@link #retrieving(BiFunction)}, but with a name that reads
     * better in some situations.
     * @see ContinuedBehaviorExpression#when(BiTransformingExpression)
     * @param expr the action
     * @param <T1> the type of the first object (in focus) the action operates on
     * @param <T2> the type of the second object (in focus) the action operates on
     * @param <S> the type of object the next expression will be operating on (next in focus)
     * @return the wrapped action
     */
    public static <T1,T2,S> BiTransformingExpression<T1, T2, S>
        transforming(BiFunction<T1, T2, S> expr) {
        return CoreExpressions.transforming(expr);
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
    public static <T, S> Function<T, S> the(Function<T, S> it) {
        return CoreExpressions.the(it);
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
    public static <T> Predicate<T> the(Predicate<T> it) {
        return CoreExpressions.the(it);
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
    public static <T1, T2, S> BiFunction<T1, T2, S> the(BiFunction<T1, T2, S> it) {
        return CoreExpressions.the(it);
    }

    public static <T> AnObject<T> a(T object) {
        return CoreExpressions.a(object);
    }

    public static <T> AnObject<T> an(T object) {
        return CoreExpressions.a(object);
    }

    /**
     * Alias for {@link Assert#assertThat(Object, Matcher)} BDD style
     * @param <T> the type of object (in focus) the action is operating on,
     * the static type accepted by the matcher
     * @param it the value being matched
     * @param is the matcher
     * @return the behavior
     */
    public static <T> Behavior<T> then(T it, Matcher<? super T> is) {
        return CoreExpressions.then(it, is);
    }

    public static Behavior<Boolean> then(boolean expr) {
        return CoreExpressions.then(expr);
    }

    /**
     * Convenience method when a behavior has been extracted to a method to be reused in another
     * test.
     * @param behavior
     * @return
     */
    public static <T> Behavior<T> then(Behavior<T> behavior) {
        return CoreExpressions.then(behavior);
    }

    public static <S> S then(Mocked<S> mocked) {
        return CoreExpressions.then(mocked);
    }

    // Mocks
    //-----------------------------------------------------------------

    public static <T> That<BDDMyOngoingStubbing<T>> that(T methodCall) {
        return MockExpressions.that(methodCall);
    }

    public static <T> Mocked<Then<T>> theMocked(T mock) {
        return MockExpressions.theMocked(mock);
    }

    // Selenium
    //-----------------------------------------------------------------

    public static <T> GivenElement<T> given(
            final Expecting<ExpectedCondition<T>> precondition) {
       return SeleniumExpressions.given(precondition);
    }

    public static <T> Expecting<ExpectedCondition<T>> expecting(
            final ExpectedCondition<T> condition, final Otherwise otherwise) {
                return SeleniumExpressions.expecting(condition,
                        otherwise);
    }

    public static WebDriver getWebDriver() {
        return SeleniumExpressions.getWebDriver();
    }

    public static void setWebDriver(final WebDriver webdriver) {
        SeleniumExpressions.setWebDriver(webdriver);
    }

}
