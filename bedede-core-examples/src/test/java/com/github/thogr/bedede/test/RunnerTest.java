// CHECKSTYLE:OFF MagicNumber

package com.github.thogr.bedede.test;

import static com.github.thogr.bedede.state.StateExpressions.expecting;
import static com.github.thogr.bedede.state.StateExpressions.otherwise;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.thogr.bedede.BehaviorRunner;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.state.Entry;

public class RunnerTest {

    private static int state = 0;

    @BeforeClass
    public static void init() {
        state = 0;
    }

    @InitialState
    public static class State1 {
        public static final Entry<State1> REACHED = new Entry<State1>() {
            @Override
            protected void perform() {
                state = 1;
            }
        };

        Expecting<BooleanCondition> isDone(final String test) {
            System.out.println(test + ": It's Done!");
            return expecting(true, otherwise("WTF!"));
        }

        @OnEntry
        Expecting<BooleanCondition> isOnEntry() {
            return expecting(state == 1, otherwise("Wrong state"));
        }

        public void moves() {
            state = 2;
        }
    }

    public static class State2 {

        private int internal = 0;

        public static final Entry<State2> REACHED = new Entry<State2>() {
            @Override
            protected void perform() {
                given(State1.class)
                .when(it->it.moves())
                .then(State2.class);
            }
        };

        @OnEntry
        Expecting<BooleanCondition> isOnEntry() {
            return expecting(state == 2, otherwise("Wrong state"));
        }

        void doesSomething() {
            internal = 123;
            System.out.println("Doing something");
        }

        Expecting<BooleanCondition> hasValue(final int val) {
            return expecting(val == internal, otherwise("Wrong value: " + val));
        }

    }

    private BehaviorRunner runner;

    @After
    public void runTest() {
        runner.run();
    }

    @Test
    public void test1() {
        runner = new BehaviorRunner() {
            @Override
            public void run() {
                System.out.println("test1: running");

                given(State1.class)
                .then(it -> it.isDone("test1"));
            }
        };
    }

    @Test
    public void test2() {
        runner = new BehaviorRunner() {
            @Override
            public void run() {
                System.out.println("test2: running");

                given(State2.class)
                .when(it -> it.doesSomething())
                .then(it -> it.hasValue(123));
            }
        };
    }
}
