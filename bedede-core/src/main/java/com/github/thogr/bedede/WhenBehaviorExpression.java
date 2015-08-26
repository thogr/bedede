package com.github.thogr.bedede;

public interface WhenBehaviorExpression<T> extends BehaviorExpression<T> {

    /**
     * Repeats an action n times.
     * Actually, it performs an already performed action n-1 more times.
     * <p>
     * Example:
     * </p>
     * <pre>
     * given(new BowlingGame())
     * .when(performing(the -&gt; the.roll(10))).times(21)
     * .then(the -&gt; the.score(), is(MAX_SCORE));
     * </pre>
     * @param n the number of times in total to perform the action
     * @return a new expression where the current object in focus is the same
     */
    Times<T> times(int n);

    /**
     * Repeats an action twice (2 times).
     * @see #times
     * @return a new expression where the current object in focus is the same
     **/
    Times<T> twice();

}
