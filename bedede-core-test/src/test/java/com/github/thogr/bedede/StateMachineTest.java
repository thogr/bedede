package com.github.thogr.bedede;

import static com.github.thogr.bedede.Bedede.given;
import static com.github.thogr.bedede.Bedede.it;
import static com.github.thogr.bedede.Bedede.performing;
import static com.github.thogr.bedede.Bedede.retrieving;
import static com.github.thogr.bedede.mockito.Expressions.that;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;
import static org.mockito.Matchers.eq;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.conditions.ConditionController;
import com.github.thogr.bedede.internal.InitialStateFactory;
import com.github.thogr.bedede.internal.StateFactory;

@RunWith(MockitoJUnitRunner.class)
public class StateMachineTest {

    @Mock
    private StateFactory factory;

    @Mock
    private InitialStateFactory initialStateFactory;

    @Mock
    private ConditionController conditionController;

    @Captor
    ArgumentCaptor<Map<String, String>> parameters;

    private StateMachineImpl machine;

    private State0 state0;
    private State1 state1;
    private State2 state2;

    @InitialState(config= {"param1=123", "param2=foo"})
    public static class State0 {

    }

    public static class State1 {

    }

    public static class State2 {

    }

    @Before
    public void setUp() throws Exception {
        machine = new StateMachineImpl(factory, initialStateFactory, conditionController);
        state0 = new State0();
        state1 = new State1();
        state2 = new State2();

        given(that(initialStateFactory.createInitialState(
                eq(factory), eq(State1.class), parameters.capture())))
        .willReturn(state1);

        given(that(initialStateFactory.createInitialState(
                eq(factory), eq(State0.class), parameters.capture())))
        .willReturn(state0);

        given(that(factory.createState(State2.class)))
        .willReturn(state2);
    }

    @Test
    public void shouldGoToInitialStateInitally() {
        given(atNoState())
        .when(retrieving(the -> the.go(State1.class)))
        .then(it(), is(sameInstance(state1)));
    }

    @Test
    public void shouldGetConfigFromInitialStateAnnotation() {
        given(atNoState())
        .when(performing(the -> the.go(State0.class)))
        .then(the -> parameters.getValue(), hasEntry("param1", "123"))
        .then(the -> parameters.getValue(), hasEntry("param2", "foo"));
    }

    @Test
    public void shouldGoToNextState() {
        given(atState(State1.class))
        .when(retrieving(the -> the.go(State2.class)))
        .then(it(), is(sameInstance(state2)));
    }

    @Test
    public void shouldNotGoToNextStateIfAlreadyThere() {
        given(that(factory.createState(State1.class)))
        .willReturn(new State1());

        given(atState(State1.class))
        .when(retrieving(the -> the.go(State1.class)))
        .then(it(), is(sameInstance(state1)));
    }

    private BehaviorExpression<StateMachineImpl> atState(Class<?> state) {
        return
        given(atNoState())
        .when(performing(the -> the.go(state)));
    }

    private GivenBehaviorExpression<StateMachineImpl> atNoState() {
        return given(machine);
    }
}
