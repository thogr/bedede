package com.github.thogr.bedede;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.core.BehaviorDriven;
import com.github.thogr.bedede.state.StateExpressions;

public class BehaviorDrivenTest extends BehaviorDriven {

    private static int entryVerified = 0;

    @Before
    public void setUp() {
        entryVerified = 0;
    }

    @InitialState
    public static class Target {
        @OnEntry
        public void onEntry() {
            entryVerified++;
        }

        public void performsAction() {
        }

        public Expecting<BooleanCondition> hasCondition() {
            return StateExpressions.expecting(true, StateExpressions.otherwise("Very wrong"));
        }
    }

    @Test
    public void shouldVerifyEntryOnGivenInitally() {
        given(Target.class);
        then(entryVerified, is(1));
    }

    @Test
    public void shouldVerifyEntryOnGivenOnlyOnce() {
        given(Target.class);
        given(Target.class);
        then(entryVerified, is(1));
    }

    @Test
    public void shouldNotVerifyEntryOnAssuming() {
        assuming(Target.class);

        assertEquals(0, entryVerified);
    }

    @Test
    public void shouldVerifyEntryOnWhenOnlyOnceAfterAsuming() {
        assuming(Target.class)
        .when(it -> it.performsAction())
        .when(it -> it.performsAction());
        then(entryVerified, is(1));
    }

    @Test
    public void shouldNotVerifyEntryOnWhenAfterGiven() {
        given(Target.class)
        .when(it -> it.performsAction());
        then(entryVerified, is(1));
    }

    @Test
    public void shouldVerifyEntryOnThen() {
        assuming(Target.class)
        .when(it -> it.performsAction())
        .then(Target.class);
        then(entryVerified, is(2));
    }

    @Test
    public void shouldVerifyEntryOnThenButNotVerifyEntryOnCondition() {
        assuming(Target.class)
        .when(it -> it.performsAction())
        .then(Target.class)
        .then(it -> it.hasCondition());
        then(entryVerified, is(2));
    }

    @Test
    public void shouldNotVerifyEntryOnCondition() {
        assuming(Target.class)
        .when(it -> it.performsAction())
        .then(it -> it.hasCondition());
        then(entryVerified, is(1));
    }
}
