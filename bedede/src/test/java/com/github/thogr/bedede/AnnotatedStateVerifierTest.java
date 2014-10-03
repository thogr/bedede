package com.github.thogr.bedede;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.annotations.State;

public class AnnotatedStateVerifierTest {

    static boolean guardCalled = false;
    private StateMachine machine;

    @State(verifier = State1.DefaultVerifier.class)
    public static class State1 {
        public static class DefaultVerifier extends AnnotatedStateVerifier<State1> {
            DefaultVerifier() {
                super(State1.class);
            }
        }

        @OnEntry
        protected void guard() {
            guardCalled = true;
        }
    }

    @State
    public static class State2 {
        @OnEntry
        protected void guard() {
            guardCalled = true;
        }
    }

    public static class State3 {
        @OnEntry
        protected void guard() {
            guardCalled = true;
        }
    }

    public static class State4 extends State3 {
    }

    public static class State5 extends State4 {
    }

    public static class State6  {
    }

    @Before
    public void setUp() {
        guardCalled = false;
        machine = new StateMachine(new DefaultStateFactory(), new DefaultInitialStateFactory());

    }

    @Test
    public void test1() {
        machine.go(State1.class);
        assertTrue(guardCalled);
    }

    @Test
    public void test2() {
        machine.go(State2.class);
        assertTrue(guardCalled);
    }

    @Test
    public void test3() {
        machine.go(State3.class);
        assertTrue(guardCalled);
    }

    @Test
    public void test4() {
        machine.go(State4.class);
        assertTrue(guardCalled);
    }

    @Test
    public void test5() {
        machine.go(State5.class);
        assertTrue(guardCalled);
    }

    @Test
    public void test6() {
        machine.go(State6.class);
        assertFalse(guardCalled);
    }

}
