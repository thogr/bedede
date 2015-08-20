package com.github.thogr.bedede;

import static com.github.thogr.bedede.Bedede.expecting;
import static com.github.thogr.bedede.Bedede.given;
import static com.github.thogr.bedede.Bedede.otherwise;
import static com.github.thogr.bedede.Bedede.performing;
import static com.github.thogr.bedede.Bedede.then;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
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

    private StateWithNoEntry stateWithNoEntry;
    private StateWithVoidEntry stateWithVoidEntry;
    private StateWithEntryCondition stateWithEntryCondition;
    private StateWithEntryInSuperClass stateWithEntryInSuperClass;
    private IllegalState1 illegalState1;
    private Throwable expected = null;

    public static class StateWithNoEntry {

    }

    public static class StateWithVoidEntry {
        public boolean isVerified = false;
        @OnEntry
        public void simpleEntryMethod() {
            isVerified = true;
        }
    }

    public static class StateWithEntryCondition {
        public static Expecting<BooleanCondition> condition =
                expecting(true, otherwise("Very, very wrong"));
        @OnEntry
        public Expecting<BooleanCondition> entryMethod() {
            return condition;
        }
    }

    public static class StateWithEntryInSuperClass extends StateWithEntryCondition {

    }

    public static class IllegalState1 {
         @OnEntry
         public void entryMethod() throws IllegalAccessException {
             throw new IllegalAccessException();
         }
    }

    @Before
    public void setUp() throws Exception {
        stateWithVoidEntry = new StateWithVoidEntry();
        stateWithEntryCondition = new StateWithEntryCondition();
        stateWithEntryInSuperClass = new StateWithEntryInSuperClass();
        illegalState1 = new IllegalState1();
    }

    @Test
    public void shouldNotVerifyWhenNoEntryMethod() {
        given(aVerifierFor(StateWithNoEntry.class))
        .when(performing(the -> the.verify(stateWithNoEntry)));
        then(conditionController).should(never()).verify(any());
    }

    @Test
    public void shouldCallSimpleEntryMethod() {
        given(aVerifierFor(StateWithVoidEntry.class))
        .when(performing(the -> the.verify(stateWithVoidEntry)))
        .then(the -> stateWithVoidEntry.isVerified);
    }

    @Test
    public void shouldVerifyConditionFromEntryMethod() {
        given(aVerifierFor(StateWithEntryCondition.class))
        .when(performing(the -> the.verify(stateWithEntryCondition)));
        then(conditionController).should().verify(StateWithEntryCondition.condition);
    }

    @Test
    public void shouldVerifyConditionFromEntryMethodInSuperclass() {
        given(aVerifierFor(StateWithEntryInSuperClass.class))
        .when(performing(the -> the.verify(stateWithEntryInSuperClass)));
        then(conditionController).should().verify(StateWithEntryCondition.condition);
    }

    @Test
    public void shouldFailWhenIllegalAccessExceptionOnEntryMethod() throws Exception {
        try {
            given(aVerifierFor(IllegalState1.class))
            .when(performing(the -> the.verify(illegalState1)));
        } catch (Exception e) {
            expected = e;
        }
        then(conditionController).should(never()).verify(any());
        then(cantVerify("IllegalState1"));
    }

    private <T> AnnotatedStateVerifier<T> aVerifierFor(Class<T> state) {
        return new AnnotatedStateVerifier<>(state, conditionController);
    }

    private Behavior<?> cantVerify(String name) {
        return
        then(expected, is(instanceOf(IllegalArgumentException.class)))
        .then(the -> expected.getMessage(),
                is("Can't verify state com.github.thogr.bedede.AnnotatedStateVerifierTest$"
                        + name));
    }
}
