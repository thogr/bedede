package com.github.thogr.bedede.examples.bowling;

import static com.github.thogr.bedede.Bedede.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorExpression;

public class BowlingKataExampleTest {

    @Test
    public void shouldGetNothingForAGutterGame() throws Exception {
        given(aGutterGame())
        .then(the -> the.score(), is(0));
    }

    @Test
    public void shouldGet20PointsForAllOnes() throws Exception {
        given(new BowlingGame())
        .when(performing(the -> the.roll(1))).times(20)
        .then(the(BowlingGame::score), is(20));
    }

    @Test
    public void shouldGetBonusForOneSpare() throws Exception {
        given(new BowlingGame())
        .when(performing(the -> the.roll(5)))
        .when(performing(the -> the.roll(5))) // Spare
        .when(performing(the -> the.roll(3)))
        .when(performing(the -> the.roll(0))).times(17)
        .then(BowlingGame::score, is(16));
    }

    @Test
    public void shouldGetBonusForOneStrike() throws Exception {
        given(new BowlingGame())
        .when(performing(the -> the.roll(10))) // Strike
        .when(performing(the -> the.roll(3)))
        .when(performing(the -> the.roll(4)))
        .when(performing(the -> the.roll(0))).times(16)
        .then(the(BowlingGame::score), is(24));
    }

    private <T> BehaviorExpression<BowlingGame> aGutterGame() {
        return given(new BowlingGame())
                .when(performing(the -> the.roll(0))).times(20);
    }
}
