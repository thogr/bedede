package com.github.thogr.bedede.core.internal;

import static com.github.thogr.bedede.core.CoreExpressions.a;
import static com.github.thogr.bedede.core.CoreExpressions.given;
import static com.github.thogr.bedede.core.CoreExpressions.performing;
import static com.github.thogr.bedede.mocks.MockExpressions.theMocked;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.thogr.bedede.Behavior;
import com.github.thogr.bedede.Entry;
import com.github.thogr.bedede.Performing;
import com.github.thogr.bedede.annotations.DefaultEntry;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.conditions.ConditionController;
import com.github.thogr.bedede.test.View2;

public class EntryTest {

    private StateMachine machine;

    private BehaviorController controller;

    private InitialStateFactory initialStateFactory;

    private StateFactory factory;

    @Mock
    private ConditionController conditionController;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        View1.mocked = Mockito.mock(Entry.class);
        factory = new DefaultStateFactory();
        initialStateFactory = new DefaultInitialStateFactory();
        machine = new StateMachineImpl(factory, initialStateFactory, conditionController);
        controller = new BehaviorController(machine, conditionController);
    }

    @InitialState
    public static class View0 {

    }

    @InitialState
    public static class View1 {

        @DefaultEntry
        public static final Entry<View1> PUBLIC = new Entry<View1>() {
            @Override
            protected void perform() {
                mocked.perform();
                then(View1.class);
            }
        };

        private static Entry<View1> mocked;

        public void opensView2() {
        }
    }

    @InitialState
    public static class View3 {
        public static Entry<View3> REACHED = new Entry<View3>() {
            @Override
            protected void perform() {
                // no then(View3.class) here
            }
        };
    }

    public <T> Behavior<StateMachine> currentStateWas(final Class<T> state) throws Exception {
        return
        given(a(machine))
        .when(performing(the -> machine.go(state)))
        .then(it -> it.was(state));
    }

    @Test
    public void shouldNotExecuteEntryForCurrentState() throws Exception {
        given(a(controller))
        .and(currentStateWas(View1.class))
        .when(executingEntry(View2.REACHED))
        .then(currentStateIs(View2.class))
        .then(theMocked(View1.mocked)).should(never()).perform();
    }

    private Performing<BehaviorController> executingEntry(Entry<?> entry) {
        return performing(it -> it.given(entry));
    }

    public boolean currentStateIs(final Class<?> state) {
        return machine.was(state);
    }

    @Test
    public void shouldExecuteEntry() throws Exception {
        given(a(controller))
        .and(currentStateWas(View0.class))
        .when(executingEntry(View2.REACHED))
        .then(currentStateIs(View2.class))
        .then(theMocked(View1.mocked)).should(times(1)).perform();
    }

    @Test
    public void shouldAdvanceState() throws Exception {
        given(a(controller))
        .when(executingEntry(View3.REACHED))
        .then(currentStateIs(View3.class));
    }
}
