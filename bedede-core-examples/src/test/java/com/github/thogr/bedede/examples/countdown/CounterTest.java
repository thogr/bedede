package com.github.thogr.bedede.examples.countdown;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorExpression;
import com.github.thogr.bedede.GivenBehaviorExpression;
import com.github.thogr.bedede.PerformingExpression;

import static com.github.thogr.bedede.Bedede.*;
import static org.hamcrest.Matchers.*;

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

    private PerformingExpression<Counter> decreasing(int n) {
        return performing(Counter::decrease).times(n);
    }

    private BehaviorExpression<Counter> initial() {
        return
            given(new Counter());
    }

    private GivenBehaviorExpression<Counter> startedWith(int startval) {
        return given(new Counter()).and(startval)
                .when(performing(Counter::start));
    }
}
