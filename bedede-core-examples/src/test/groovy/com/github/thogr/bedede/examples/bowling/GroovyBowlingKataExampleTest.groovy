// CHECKSTYLE:OFF MagicNumber

package com.github.thogr.bedede.examples.bowling

import static com.github.thogr.bedede.GroovyCoreExpressions.*
import static org.hamcrest.CoreMatchers.*

import org.junit.Test

class GroovyBowlingKataExampleTest {

    @Test
    void shouldGetNothingForAGutterGame() throws Exception {
        given a: new BowlingGame()
        when performing: {the.roll(0)}, times: 20
        then ({the.score()}, is(0))
    }

    @Test
    void shouldGet20PointsForAllOnes() throws Exception {
        given a: new BowlingGame()
        when performing: {the.roll(1)}, times: 20
        then expect: {the.score()}, is(20)
    }

    @Test
    public void shouldGetBonusForOneSpare() throws Exception {
        given a: new BowlingGame()
        when performing: {the.roll(5)}
        when performing: {the.roll(5)} // Spare
        when performing: {the.roll(3)}
        when performing: {the.roll(0)}, times: 17
        then expect: {the.score()}, is: equalTo(16)
    }

    @Test
    public void shouldGetBonusForOneStrike() throws Exception {
        given a: new BowlingGame()
        when performing: {the.roll(10)} // Strike
        when performing: {the.roll(3)}
        when performing: {the.roll(4)}
        when performing: {the.roll(0)}, times:16
        then expect: {the.score()}, is: 24
    }
}
