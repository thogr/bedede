package com.github.thogr.bedede;

import static com.github.thogr.bedede.Bedede.*;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Matchers.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.github.thogr.bedede.conditions.ConditionController;
import com.github.thogr.bedede.internal.InitialStateFactory;
import com.github.thogr.bedede.internal.StateFactory;

public class StateMachineTest {

    @Mock
    private StateFactory factory;

    @Mock
    private InitialStateFactory initialStateFactory;

    @Mock
    private ConditionController conditionController;

    private StateMachine machine;

    private State1 state1;
    private State2 state2;

    public static class State1 {

    }

    public static class State2 {

    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        machine = new StateMachine(factory, initialStateFactory, conditionController);
        state1 = new State1();
        state2 = new State2();

        BDDMockito
        .given(initialStateFactory.createInitialState(eq(factory), eq(State1.class), any()))
        .willReturn(state1);

        BDDMockito
        .given(factory.createState(State2.class))
        .willReturn(state2);
    }

    @Test
    public void shouldGoToInitialStateInitally() {
        given(atNoState())
        .when(retrieving(the -> the.go(State1.class)))
        .then(it(), is(sameInstance(state1)));
    }

    @Test
    public void shouldGoToNextState() {
        given(atState(State1.class))
        .when(retrieving(the -> the.go(State2.class)))
        .then(it(), is(sameInstance(state2)));
    }

    @Test
    public void shouldNotGoToNextStateIfAlreadyThere() {
        BDDMockito
        .given(factory.createState(State1.class))
        .willReturn(new State1());

        given(atState(State1.class))
        .when(retrieving(the -> the.go(State1.class)))
        .then(it(), is(sameInstance(state1)));
    }

    private BehaviorExpression<StateMachine> atState(Class<?> state) {
        return
        given(atNoState())
        .when(performing(the -> the.go(state)));
    }

    private GivenBehaviorExpression<StateMachine> atNoState() {
        return given(machine);
    }

}
