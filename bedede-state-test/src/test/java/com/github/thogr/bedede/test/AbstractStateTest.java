package com.github.thogr.bedede.test;

import static com.github.thogr.bedede.StateExpressions.at;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.state.AbstractState;
import com.github.thogr.bedede.state.Entry;

public class AbstractStateTest extends BehaviorDriven {

    private static int state = 0;

    public static class State1 {
        private static boolean guardCalled;

        public static final Entry<State1> REACHED = new Entry<State1>() {
            @Override
            protected void perform() {
                state = 1;
                then(State1.class);
            }
        };

        @OnEntry
        private void guard() {
            guardCalled = true;
            assertTrue(state == 1);
        }
    }

    public static class State2 extends AbstractState<BooleanCondition> {
        private static boolean guardCalled;

        public static final Entry<State2> REACHED = new Entry<State2>() {
            @Override
            protected void perform() {
                state = 2;
                then(State2.class);
            }
        };

        @Override
        protected Expecting<BooleanCondition> onEntry() {
            guardCalled = true;
            assertTrue(state == 2);
            return null;
        }
    }

    @Before
    public void setUp() {
        state = 0;
        State1.guardCalled = false;
        State2.guardCalled = false;
    }

    @Test
    public void test1() {
        given(at(State1.class));
        assertTrue(state == 1);
        assertTrue(State1.guardCalled);
    }

    @Test
    public void test2() {
        given(at(State2.class));
        assertTrue(state == 2);
        assertTrue(State2.guardCalled);
    }
}
