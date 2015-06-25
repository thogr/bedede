package com.github.thogr.bedede;

import static com.github.thogr.bedede.Bedede.expecting;
import static com.github.thogr.bedede.Bedede.given;
import static com.github.thogr.bedede.Bedede.otherwise;
import static com.github.thogr.bedede.Bedede.performing;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.ConditionController;
import com.github.thogr.bedede.conditions.Expecting;

@RunWith(MockitoJUnitRunner.class)
public class AnnotatedStateVerifierTest {

    @Mock
    private ConditionController conditionController;

    private State0 state0;
    private State1 state1;
    private State2 state2;
    private State3 state3;

    public static class State0 {

    }

    public static class State1 {
        public boolean isVerified = false;
        @OnEntry
        public void simpleEntryMethod() {
            isVerified = true;
        }
    }

    public static class State2 {
        public static Expecting<BooleanCondition> condition =
                expecting(true, otherwise("Very, very wrong"));
        @OnEntry
        public Expecting<BooleanCondition> entryMethod() {
            return condition;
        }
    }

    public static class State3 extends State2 {

    }


    @Before
    public void setUp() throws Exception {
        state1 = new State1();
        state2 = new State2();
        state3 = new State3();
    }

    @Test
    public void shouldNotVerifyWhenNoEntryMethod() {
        given(new AnnotatedStateVerifier<State0>(State0.class, conditionController))
        .when(performing(the -> the.verify(state0)));
        then(conditionController).should(never()).verify(any());
    }

    @Test
    public void shouldCallSimpleEntryMethod() {
        given(new AnnotatedStateVerifier<State1>(State1.class, conditionController))
        .when(performing(the -> the.verify(state1)))
        .then(the -> state1.isVerified);
    }

    @Test
    public void shouldVerifyConditionFromEntryMethod() {
        given(new AnnotatedStateVerifier<State2>(State2.class, conditionController))
        .when(performing(the -> the.verify(state2)));
        then(conditionController).should().verify(State2.condition);
    }

    @Test
    public void shouldVerifyConditionFromEntryMethodInSuperclass() {
        given(new AnnotatedStateVerifier<State3>(State3.class, conditionController))
        .when(performing(the -> the.verify(state3)));
        then(conditionController).should().verify(State2.condition);
    }
}
