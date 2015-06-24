package com.github.thogr.bedede.examples.countdown;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorExpression;
import com.github.thogr.bedede.PerformingExpression;

import static com.github.thogr.bedede.Bedede.*;
import static org.hamcrest.Matchers.*;

public class CountdownTest {

    @Test
    public void shouldBeStoppedInitially() {
        given(initial())
        .then(Countdown::isStopped);
    }

    @Test
    public void shouldNotBeStoppedWhenStarted() {
        given(startedWith(1))
        .then(Countdown::isStopped, is(false));
    }

    @Test
    public void shouldNotBeStoppedWhenStartedAndDecreasedTooFew() {
        given(startedWith(3))
        .when(performing(Countdown::decrease).twice())
        .then(Countdown::isStopped, is(false));
    }

    @Test
    public void shouldNotBeStoppedWhenStartedAndDecreasedToZero() {
        given(startedWith(3))
        .when(decreasing(3))
        .then(Countdown::isStopped);
    }

    private PerformingExpression<Countdown> decreasing(int n) {
        return performing(Countdown::decrease).times(n);
    }

    private BehaviorExpression<Countdown> initial() {
        return
            given(new Countdown());
    }

    private BehaviorExpression<Countdown> startedWith(int startval) {
        return given(initial()).and(startval)
                .when(performing(Countdown::start));
    }
}
