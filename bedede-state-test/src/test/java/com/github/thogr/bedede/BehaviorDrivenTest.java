package com.github.thogr.bedede;

import static com.github.thogr.bedede.CoreExpressions.performing;
import static com.github.thogr.bedede.StateExpressions.at;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;

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
        given(at(Target.class));
        then(entryVerified, is(1));
    }

    @Test
    public void shouldVerifyEntryOnGivenOnlyOnce() {
        given(at(Target.class));
        given(at(Target.class));
        then(entryVerified, is(1));
    }

    @Test
    public void shouldNotVerifyEntryOnAssuming() {
        assuming(at(Target.class));
        assertEquals(0, entryVerified);
    }

    @Test
    public void shouldVerifyEntryOnWhenOnlyOnceAfterAsuming() {
        assuming(at(Target.class))
        .when(performing(it -> it.performsAction()))
        .when(performing(it -> it.performsAction()));
        then(entryVerified, is(1));
    }

    @Test
    public void shouldNotVerifyEntryOnWhenAfterGiven() {
        given(at(Target.class))
        .when(performing(it -> it.performsAction()));
        then(entryVerified, is(1));
    }

    @Test
    public void shouldVerifyEntryOnThen() {
        assuming(at(Target.class))
        .when(performing(it -> it.performsAction()))
        .then(at(Target.class));
        then(entryVerified, is(2));
    }

    @Test
    public void shouldVerifyEntryOnThenButNotVerifyEntryOnCondition() {
        assuming(at(Target.class))
        .when(performing(it -> it.performsAction()))
        .then(at(Target.class))
        .then(it -> it.hasCondition());
        then(entryVerified, is(2));
    }

    @Test
    public void shouldNotVerifyEntryOnCondition() {
        assuming(at(Target.class))
        .when(performing(it -> it.performsAction()))
        .then(it -> it.hasCondition());
        then(entryVerified, is(1));
    }
}
