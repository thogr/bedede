// CHECKSTYLE:OFF MagicNumber

package com.github.thogr.bedede.examples.count

import static com.github.thogr.bedede.core.GroovyCoreExpressions.*
import static org.hamcrest.Matchers.is

import org.junit.Test

class GroovyCounterTest {

    @Test
    void shouldBeStoppedInitially() {
        given initial()
        then expect: {it.isStopped()}
    }

    @Test
    void shouldNotBeStoppedWhenStarted() {
        given startedWith(3)
        when performing: {the.decrease()}
        then expect: {it.isStopped()}, is: false
    }

    @Test
    void shouldNotBeStoppedWhenStartedAndDecreasedTooFew() {
        given startedAt(3)
        when performing: {the.decrease()}, times: 2
        then expect: {it.isStopped()}, is: false
    }

    @Test
    void shouldBeStoppedWhenStartedAndDecreasedToZero() {
        given startedOn(3)
        when decreasing(3)
        then {it.isStopped()}
    }

    def decreasing(final int n) {
        return performing({the.decrease()}).times(n)
    }

    def initial() {
        given a: new Counter()
    }

    def startedWith(final int startval) {
        given a: new Counter()
        and a: startval
        with {c, s -> c.start(s)}
    }

    def startedAt(final int startval) {
        given a: new Counter()
        with {it.start(startval)}
    }

    def startedOn(final int startval) {
        given a: new Counter()
        when performing: {the.start(startval)}
    }
}
