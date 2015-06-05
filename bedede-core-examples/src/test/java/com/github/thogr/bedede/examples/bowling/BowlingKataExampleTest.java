package com.github.thogr.bedede.examples.bowling;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorExpression;

import static com.github.thogr.bedede.Bedede.*;

public class BowlingKataExampleTest {

    @Test
    public void shouldGetNothingForAGutterGame() throws Exception {
        given(aGutterGame())
        .then(the -> the.score(), is(0));
    }

    @Test
    public void shouldGet20PointsForAllOnes() throws Exception {
        given(new Game())
        .when(it -> it.roll(1)).times(20)
        .then(the -> the.score(), is(20));
    }

    @Test
    public void shouldGetBonusForOneSpare() throws Exception {
        given(new Game())
        .when(it -> it.roll(5))
        .when(it -> it.roll(5)) // Spare
        .when(it -> it.roll(3))
        .when(it -> it.roll(0)).times(17)
        .then(the -> the.score(), is(16));
    }

    @Test
    public void shouldGetBonusForOneStrike() throws Exception {
        given(new Game())
        .when(it -> it.roll(10)) // Strike
        .when(it -> it.roll(3))
        .when(it -> it.roll(4))
        .when(it -> it.roll(0)).times(16)
        .then(the -> the.score(), is(24));
    }

    private BehaviorExpression<Game> aGutterGame() {
        return given(new Game())
                .when(it -> it.roll(0)).times(20);
    }
}
