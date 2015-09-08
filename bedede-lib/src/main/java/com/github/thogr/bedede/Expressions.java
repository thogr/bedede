// CHECKSTYLE:OFF FileLength
// CHECKSTYLE:OFF FanOutComplexity

package com.github.thogr.bedede;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

import org.hamcrest.Matcher;
import org.mockito.BDDMockito;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.core.CoreExpressions;
import com.github.thogr.bedede.mocks.MockExpressions;
import com.github.thogr.bedede.mocks.Mocked;
import com.github.thogr.bedede.mocks.That;
import com.github.thogr.bedede.state.Assuming;
import com.github.thogr.bedede.state.Entry;
import com.github.thogr.bedede.state.GivenElement;
import com.github.thogr.bedede.state.StateExpressions;
import com.github.thogr.bedede.state.internal.Defining.DefiningEntry;


public abstract class Expressions {

    // CoreExpressions
    //-----------------------------------------------------------------

    /**
     * Creates an "expecting" using a boolean condition. Typically in an &#64;OnEntry method.
     * <p>Example:<p>
     * <pre>
     *   &#64;OnEntry
     *   public Expecting<BooleanCondition> shouldBeLocked() {
     *       return expecting(door.isLocked(), otherwise("Unexpected unlocked door"));
     *   }
     * </pre>
     * @param condition the boolean condition
     * @param otherwise description of otherwise
     * @return the expecting
     */
    public static Expecting<BooleanCondition> expecting(
    final Boolean condition, final Otherwise otherwise) {
        return StateExpressions.expecting(condition, otherwise);
    }

    /**
     * Wraps a description of the unexpected
     * @see StateExpressions#expecting(Boolean, Otherwise)
     * @param message the text to be used as error message
     * @return the wrapped description
     */
    public static Otherwise otherwise(final String message) {
        return CoreExpressions.otherwise(message);
    }

    /**
     * Start defining an entry.
     * @param state the state class, where the entry should be
     * @param <T> the type of the state class
     * @return the start (to be continued) defining the entry
     */
    public static <T> DefiningEntry<T> entry(final Class<T> state) {
        return StateExpressions.entry(state);
    }

    /**
     * Start defining a behavior.
     * @return the prefix for continued definition of the behavior
     */
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
        return StateExpressions.given(state);
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
        return StateExpressions.given(entry);
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
    public static <T> Given<T> given(final Behavior<T> expr) {
        return CoreExpressions.given(expr);
    }

    /**
     * Sets the starting environment for a test using mocks. This method merely calls the mocking
     * framework, but is needed because of possible name conflicts in the mock framework.
     * @param that a wrapped mocked object
     * @param <T> the type of the mock
     * @return the mock
     */
    public static <T> T given(final That<T> that) {
        return CoreExpressions.given(that);
    }

    /**
     * Wraps an action that operates on an object into a performing expression.
     * The object originates from a given(object) expression.
     * @see com.github.thogr.bedede.BehaviorExpression#when(PerformingImpl)
     * @param expr the action
     * @param <T> the type of object (in focus) the action is operating on
     * @return the wrapped action
     */
    public static <T> Performing<T> performing(final ActionExpression<T> expr) {
        return CoreExpressions.performing(expr);
    }

    /**
     * Wraps an action that operates on two objects into a performing expression.
     * The objects originates from a given(object1).given(object2)
     * or given(object1).and(object2) expression.
     * @see com.github.thogr.bedede.BehaviorExpression#when(PerformingImpl)
     * @param expr the action
     * @param <T1> the type of the first object (in focus) the action operates on
     * @param <T2> the type of the second object (in focus) the action operates on
     * @return the wrapped action
     */
    public static <T1, T2> BiPerforming<T1, T2> performing(
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
     * @see com.github.thogr.bedede.BehaviorExpression#when(Transforming)
     * @param expr the action
     * @param <T> the type of object (in focus) the action is operating on
     * @param <S> the type of object the next expression will be operating on (next in focus)
     * @return the wrapped action
     */
    public static <T, S> Transforming<T, S> retrieving(final Function<T, S> expr) {
        return CoreExpressions.retrieving(expr);
    }

    /**
     * Wraps an action into a transforming expression.
     * @see com.github.thogr.bedede.BehaviorExpression#when(Transforming)
     * @param expr the action
     * @param <T> the type of object (in focus) the action is operating on
     * @param <S> the type of object the next expression will be operating on (next in focus)
     * @return the wrapped action
     */
    public static <T, S> Transforming<T, S> transforming(final Function<T, S> expr) {
        return CoreExpressions.transforming(expr);
    }

    /**
     * Wraps an action that operates on two objects into a transforming expression.
     * This is an alias for {@link #transforming(BiFunction)}, but with a name that reads
     * better in some situations.
     * @see com.github.thogr.bedede.ContinuedBehaviorExpression#when(BiTransformingImpl)
     * @param expr the action
     * @param <T1> the type of the first object (in focus) the action operates on
     * @param <T2> the type of the second object (in focus) the action operates on
     * @param <S> the type of object the next expression will be operating on (next in focus)
     * @return the wrapped action
     */
    public static <T1, T2, S> BiTransforming<T1, T2, S>
        retrieving(final BiFunction<T1, T2, S> expr) {
        return CoreExpressions.retrieving(expr);
    }

    /**
     * Wraps an action that operates on two objects into a transforming expression.
     * This is an alias for {@link #retrieving(BiFunction)}, but with a name that reads
     * better in some situations.
     * @see com.github.thogr.bedede.ContinuedBehaviorExpression#when(BiTransformingImpl)
     * @param expr the action
     * @param <T1> the type of the first object (in focus) the action operates on
     * @param <T2> the type of the second object (in focus) the action operates on
     * @param <S> the type of object the next expression will be operating on (next in focus)
     * @return the wrapped action
     */
    public static <T1, T2, S> BiTransforming<T1, T2, S>
        transforming(final BiFunction<T1, T2, S> expr) {
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
    public static <T, S> Function<T, S> the(final Function<T, S> it) {
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
    public static <T> Predicate<T> the(final Predicate<T> it) {
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
    public static <T1, T2, S> BiFunction<T1, T2, S> the(final BiFunction<T1, T2, S> it) {
        return CoreExpressions.the(it);
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
        return CoreExpressions.a(object);
    }

    /**
     * Alias for {@link CoreExpressions#a(Object)}
     * @param object any object
     * @param <T> the type of the object in focus
     * @return the wrapped object
     */
    public static <T> AnObject<T> an(final T object) {
        return CoreExpressions.a(object);
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
        return CoreExpressions.then(it, is);
    }

   /**
    * Convenience method corresponding to a call to {@link org.junit.Assert#assertTrue(boolean)}
    * @param expr boolean expression
    * @return the behavior
    */
    public static Then<Boolean> then(final boolean expr) {
        return CoreExpressions.then(expr);
    }

    /**
     * Convenience method when a behavior has been extracted to a method to be reused in another
     * test.
     * @param behavior the other (reused) behavior
     * @param <T> the type of the object in focus
     * @return this behavior
     */
    public static <T> Then<T> then(final Behavior<T> behavior) {
        return CoreExpressions.then(behavior);
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
     */
    public static <S> S then(final Mocked<S> mocked) {
        return CoreExpressions.then(mocked);
    }

    // Mocks
    //-----------------------------------------------------------------

    public static <T> That<BDDMyOngoingStubbing<T>> that(final T methodCall) {
        return MockExpressions.that(methodCall);
    }

    public static <T> Mocked<BDDMockito.Then<T>> theMocked(final T mock) {
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
