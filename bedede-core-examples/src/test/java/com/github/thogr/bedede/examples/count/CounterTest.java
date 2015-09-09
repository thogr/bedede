// CHECKSTYLE:OFF MagicNumber

package com.github.thogr.bedede.examples.count;

import static com.github.thogr.bedede.CoreExpressions.a;
import static com.github.thogr.bedede.CoreExpressions.given;
import static com.github.thogr.bedede.CoreExpressions.performing;
import static org.hamcrest.Matchers.is;

import org.junit.Test;

import com.github.thogr.bedede.core.BehaviorExpression;
import com.github.thogr.bedede.core.Performing;

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
        given(startedAt(3))
        .when(performing(Counter::decrease).twice())
        .then(Counter::isStopped, is(false));
    }

    @Test
    public void shouldBeStoppedWhenStartedAndDecreasedToZero() {
        given(startedOn(3))
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
                .with(Counter::start);
    }

    private BehaviorExpression<Counter> startedAt(final int startval) {
        return given(a(new Counter()))
                .with(it -> it.start(startval));
    }

    private BehaviorExpression<Counter> startedOn(final int startval) {
        return given(a(new Counter())).and(a(startval))
                .when(performing(Counter::start));
    }

}
