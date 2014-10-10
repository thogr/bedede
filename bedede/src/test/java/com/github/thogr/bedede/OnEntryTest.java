package com.github.thogr.bedede;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.ConditionController;
import com.github.thogr.bedede.internal.DefaultInitialStateFactory;
import com.github.thogr.bedede.internal.DefaultStateFactory;

public class OnEntryTest {

    @Mock
    ConditionController conditionController;

    static boolean guardCalled = false;
    private StateMachine machine;

    public static class State1 {
        @OnEntry
        protected void guard() {
            guardCalled = true;
        }
    }

    public static class State2 extends State1{
    }

    public static class State3 extends State2 {
    }

    public static class StateWithNoOnEntryMethod  {
    }

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        guardCalled = false;
        machine =
                new StateMachine(new DefaultStateFactory(), new DefaultInitialStateFactory(), conditionController);

    }

    @Test
    public void test1() {
        machine.go(State1.class);
        assertTrue(guardCalled);
    }
    @Test
    public void test4() {
        machine.go(State2.class);
        assertTrue(guardCalled);
    }

    @Test
    public void test5() {
        machine.go(State3.class);
        assertTrue(guardCalled);
    }

    @Test
    public void test6() {
        machine.go(StateWithNoOnEntryMethod.class);
        assertFalse(guardCalled);
    }

}
