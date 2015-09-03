// CHECKSTYLE:OFF MagicNumber

package com.github.thogr.bedede.examples.countdown;

import static com.github.thogr.bedede.core.CoreExpressions.a;
import static com.github.thogr.bedede.core.CoreExpressions.given;
import static com.github.thogr.bedede.core.CoreExpressions.performing;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorExpression;
import com.github.thogr.bedede.Performing;

public class CounterTest {

    @Test
    public void shouldBeStoppedInitially() {
        given(initial())
        .then(Counter::isStopped);
    }

    @Test
    public void shouldNotBeStoppedWhenStarted() {
        given(startedWith(3))
        .when(performing(Counter::decrease))
        .then(Counter::isStopped, is(false));
    }

    @Test
    public void shouldNotBeStoppedWhenStartedAndDecreasedTooFew() {
        given(startedWith(3))
        .when(performing(Counter::decrease).twice())
        .then(Counter::isStopped, is(false));
    }

    @Test
    public void shouldBeStoppedWhenStartedAndDecreasedToZero() {
        given(startedWith(3))
        .when(decreasing(3))
        .then(Counter::isStopped);
    }

    private Performing<Counter> decreasing(final int n) {
        return performing(Counter::decrease).times(n);
    }

    private BehaviorExpression<Counter> initial() {
        return
            given(a(new Counter()));
    }

    private BehaviorExpression<Counter> startedWith(final int startval) {
        return given(a(new Counter())).and(a(startval))
                .when(performing(Counter::start));
    }
}
