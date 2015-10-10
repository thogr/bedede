package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.StateExpressions.at;

import org.junit.Before;
import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.annotations.OnEntry;

public class TransitionTest extends BehaviorDriven {

    private static SystemTested system = new SystemTested();

    @InitialState
    public static class State1 {

        @OnEntry
        void guard() {
            assert system.getState() == 0 : "Not state 1";
        }

        void moves() {
            system.doSomeThing();
        }
    }

    public static class State2 {
        @OnEntry
        void guard() {
            assert system.getState() == 1 : "Not state 2";
        }
    }

    @Before
    public void setUp() {
        system = new SystemTested();
    }

    @Test
    public void exampleOneStatement() throws Exception {
        given(at(State1.class)).
        when(it->it.moves()).
        then(at(State2.class));
    }

    @Test
    public void exampleTwoStatements() throws Exception {
        given(at(State1.class));

        assuming(at(State1.class)).
        when(it->it.moves()).
        then(at(State2.class));
    }

    @Test
    public void exampleThreeStatements() throws Exception {
        given(at(State1.class));

        assuming(at(State1.class)).
        when(it->it.moves());
        then(at(State2.class));
    }

}
