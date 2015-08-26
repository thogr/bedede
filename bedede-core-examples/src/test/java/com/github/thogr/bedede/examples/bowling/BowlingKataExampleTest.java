package com.github.thogr.bedede.examples.bowling;

import static com.github.thogr.bedede.core.CoreExpressions.a;
import static com.github.thogr.bedede.core.CoreExpressions.given;
import static com.github.thogr.bedede.core.CoreExpressions.performing;
import static com.github.thogr.bedede.core.CoreExpressions.the;
import static org.hamcrest.CoreMatchers.is;

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
        given(a(new BowlingGame()))
        .when(performing(the -> the.roll(1))).times(20)
        .then(the(BowlingGame::score), is(20));
    }

    @Test
    public void shouldGetBonusForOneSpare() throws Exception {
        given(a(new BowlingGame()))
        .when(performing(the -> the.roll(5)))
        .when(performing(the -> the.roll(5))) // Spare
        .when(performing(the -> the.roll(3)))
        .when(performing(the -> the.roll(0))).times(17)
        .then(BowlingGame::score, is(16));
    }

    @Test
    public void shouldGetBonusForOneStrike() throws Exception {
        given(a(new BowlingGame()))
        .when(performing(the -> the.roll(10))) // Strike
        .when(performing(the -> the.roll(3)))
        .when(performing(the -> the.roll(4)))
        .when(performing(the -> the.roll(0))).times(16)
        .then(the(BowlingGame::score), is(24));
    }

    private BehaviorExpression<BowlingGame> aGutterGame() {
        return given(a(new BowlingGame()))
                .when(performing(the -> the.roll(0))).times(20);
    }
}
