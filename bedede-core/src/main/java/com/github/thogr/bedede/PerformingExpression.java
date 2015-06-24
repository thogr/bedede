package com.github.thogr.bedede;

import com.github.thogr.bedede.ActionExpression;

public final class PerformingExpression<T> {
    private ActionExpression<T> action;
    private final int count;

    PerformingExpression(ActionExpression<T> expr) {
        this(expr, 1);
    }

    PerformingExpression(ActionExpression<T> action, int count) {
        this.action = action;
        this.count = count;
    }

    void perform(T it) {
        for (int i = 1; i <= count; i++) {
            action.perform(it);
        }
    }

    /**
     * Repeats an action n times.
     * <p>
     * Example:
     * </p>
     * <pre>
     * given(new BowlingGame())
     * .when(performing(the -&gt; the.roll(10)).times(21))
     * .then(the -&gt; the.score(), is(MAX_SCORE));
     * </pre>
     * @param n the number of times in total to perform the action
     * @return a new expression where the current object in focus is the same
     */
    public PerformingExpression<T> times(int n) {
        return new PerformingExpression<T>(action, n);
    }

    /**
     * Repeats an action twice (2 times).
     * @see #times
     * @return a new expression where the current object in focus is the same
     **/
    public PerformingExpression<T> twice() {
        return times(2);
    }
}
