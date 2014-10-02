package com.github.thogr.bedede;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.verification.VerificationMode;

import com.github.thogr.bedede.annotations.DefaultEntry;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.test.View2;

public class GivenTest {

    private StateMachine machine;

    private BehaviorController controller;

    private InitialStateFactory initialStateFactory;

    private StateFactory factory;

    @SuppressWarnings("unchecked")
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        View1.mocked = Mockito.mock(Entry.class);
        factory = new DefaultStateFactory();
        initialStateFactory = new DefaultInitialStateFactory();
        machine = new StateMachine(factory, initialStateFactory);
        controller = new BehaviorController(machine);
    }

    public static class AbstractTestBaseState extends AbstractState {

        @Override
        protected void onEntry() {

        }
    }

    @InitialState(config="a=0")
    public static class View0 extends AbstractTestBaseState {

    }

    @InitialState(config="a=0")
    public static class View1 extends AbstractTestBaseState {

        @DefaultEntry
        public static final Entry<View1> PUBLIC = new Entry<View1>() {
            @Override
            protected void perform() {
                mocked.perform();
                then(View1.class);
            }
        };

        private static Entry<View1> mocked;

        public void openingView2() {
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
        thenExecuted(View1.mocked, never());
    }

    public void thenCurrentState(final Class<?> state) {
        assertThat(machine.was(state), is(true));
    }

    @Test
    public void shouldExecuteEntry() throws Exception {
        givenCurrentState(View0.class);
        whenExecuted(View2.REACHED);
        thenCurrentState(View2.class);
        thenExecuted(View1.mocked, times(1));
    }

    public void thenExecuted(final Entry<View1> given, final VerificationMode verification) {
        verify(given, verification).perform();
    }

    public void whenExecuted(final Entry<?> entry) {
        controller.given(entry);
    }
}
