package com.github.thogr.bedede;

/**
 * The result of a non-transforming when() expression.
 *
 * @param <T>
 */
public final class WhenBehaviorExpression<T> extends BehaviorExpression<T> {

    ActionExpression<? super T> action;

    WhenBehaviorExpression(T obj, ActionExpression<? super T> action) {
        super(obj);
        this.action = action;
    }

    /**
     * Repeats an action n times.
     * Actually, it performs an already performed action n-1 more times.
     * <p>
     * Example:<nl/>
     * <pre>
     * given(new BowlingGame())
     * .when(performing(the -> the.roll(10))).times(21)
     * .then(the -> the.score(), is(MAX_SCORE));
     * </pre>
     * </p>
     * @param n the number of times in total to perform the action
     * @return
     */
    public BehaviorExpression<T> times(int n) {
        for (int i = 1; i < n; i++) {
            action.perform(obj);
        }
        return new BasicBehaviorExpression<T>(obj);
    }
}
