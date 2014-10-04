package com.github.thogr.bedede;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.github.thogr.bedede.annotations.DefaultEntry;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.conditions.ConditionController;
import com.github.thogr.bedede.test.View2;

public class GivenTest {

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
        machine = new StateMachine(factory, initialStateFactory);
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

    public <T> void givenCurrentState(final Class<T> state) throws Exception {
        machine.go(state);
        assertThat(machine.was(state), is(true));
    }

    @Test
    public void shouldNotExecuteEntryForCurrentState() throws Exception {
        givenCurrentState(View1.class);
        whenExecuted(View2.REACHED);
        thenCurrentState(View2.class);
        then(View1.mocked).should(never()).perform();
    }

    public void thenCurrentState(final Class<?> state) {
        assertThat(machine.was(state), is(true));
    }

    @Test
    public void shouldExecuteEntry() throws Exception {
        givenCurrentState(View0.class);
        whenExecuted(View2.REACHED);
        thenCurrentState(View2.class);
        then(View1.mocked).should(times(1)).perform();
    }

    public void whenExecuted(final Entry<?> entry) {
        controller.given(entry);
    }
}
