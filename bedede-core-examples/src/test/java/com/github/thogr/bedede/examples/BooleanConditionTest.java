package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.state.StateExpressions.expecting;
import static com.github.thogr.bedede.state.StateExpressions.given;
import static com.github.thogr.bedede.state.StateExpressions.otherwise;

import org.junit.Before;
import org.junit.Test;

import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;

public class BooleanConditionTest {
    private static final String KALLE = "Kalle";

    private static String name = KALLE;
    private static int status = 1;

    @Before
    public void setUp() {
        name = KALLE;
    }

    @InitialState
    public static class State1 {

        Expecting<BooleanCondition> shouldHaveStatus(final int someStatus) {
            return expecting(status == someStatus, otherwise("Unexpected status: " + someStatus));
        }

        Expecting<BooleanCondition> shouldHaveName(final String someName) {
            return expecting(name.equals(someName), otherwise("Unexpected name: " + someName));
        }

        void changesNameTo(final String newName) {
            name = newName;
        }

        @OnEntry
        public Expecting<BooleanCondition> onEntry() {
            return shouldHaveStatus(1).and(shouldHaveName(KALLE));
        }
    }

    @Test
    public void example() {
        given(State1.class)
        .when(it -> it.changesNameTo("Nisse"))
        .then(it -> it.shouldHaveStatus(1))
        .then(it -> it.shouldHaveName("Nisse"));
    }
}
