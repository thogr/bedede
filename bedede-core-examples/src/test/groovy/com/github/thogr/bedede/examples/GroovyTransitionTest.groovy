package com.github.thogr.bedede.examples

import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.assertThat

import org.junit.Before
import org.junit.Test

import com.github.thogr.bedede.annotations.InitialState
import com.github.thogr.bedede.annotations.OnEntry
import com.github.thogr.bedede.core.BehaviorDriven;

class GroovyTransitionTest extends BehaviorDriven {

    static SystemTested system

    @Before
    void setUp() {
        system = new SystemTested()
    }

    @InitialState
    static class State1 {

        @OnEntry
        def guard() {
            assert system.getState() == 0 : "Not state 1"
        }

        def moves() {
            system.doSomeThing()
        }
    }

    static class State2 {
        @OnEntry
        def guard() {
            assert system.getState() == 1 : "Not state 2"
        }
    }

    @Test
    void exampleOneStatement() {
        given (State1)
            .when {it.moves()}
            .then State2
    }

    @Test
    void exampleOneStatementWithGivenClosure() {
        given (State1) {
            when {it.moves()}
            .then State2
        }
    }

    @Test
    void exampleOneStatementWithGivenClosureWithThenInside() {
        given (State1) {
            when {it.moves()}
            then State2
        }
    }

    @Test
    void exampleTwoStatements() {
        given State1

        assuming (State1)
                .when {it.moves()}
                .then State2
    }

    @Test
    void exampleTwoStatementsWithAssumingClosure() {
        given State1

        assuming (State1) {
            when {it.moves()}.then State2
        }
    }

    @Test
    void exampleTwoStatementsWithGivenClosure() {
        given (State1) {
            when {it.moves()}
        }
        then State2
    }


    @Test
    void exampleThreeStatements() {
        given State1

        assuming (State1).when {
            it.moves()
        }

        then State2
    }

    @Test
    void exampleThreeStatementsWithAssumingClosure() {
        given State1

        assuming (State1) {
            when {it.moves()}
        }

        then State2
    }

}
