package com.github.thogr.bedede.state.internal;

import static com.github.thogr.bedede.CoreExpressions.a;
import static com.github.thogr.bedede.CoreExpressions.given;
import static com.github.thogr.bedede.CoreExpressions.retrieving;
import static com.github.thogr.bedede.MockitoExpressions.that;
import static com.github.thogr.bedede.MockitoExpressions.theMocked;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.never;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.thogr.bedede.annotations.DefaultEntry;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.state.Assuming;
import com.github.thogr.bedede.state.Entry;

@RunWith(MockitoJUnitRunner.class)
public class BehaviorControllerTest {

    private static class TargetState {

    }

    @InitialState
    private static class InitState {
        public void goOther() {
        }
    }

    private static class OtherTargetState {
        @DefaultEntry
        public static final Entry<OtherTargetState> DEFAULT = new Entry<OtherTargetState>() {

            @Override
            protected void perform() {
                given(InitState.class)
                .when(it -> {
                    it.goOther();
                })
                .then(OtherTargetState.class);
            }
        };

    }

    @Mock
    private StateMachine machine;

    @Mock
    private ConditionController conditionController;

    @Test
    public void shouldAssumeTheCurrentState() {
        given(that(machine.was(TargetState.class))).willReturn(true);
        given(a(new BehaviorController(machine, conditionController)))
        .when(retrieving(the -> the.given(TargetState.class)))
        .then((it, result) -> is(assuming(TargetState.class, it)));
    }

    @Test
    public void shouldAssumeInitialStateInitally() {
        given(that(machine.was(InitState.class))).willReturn(false);
        given(a(new BehaviorController(machine, conditionController)))
        .when(retrieving(the -> the.given(InitState.class)))
        .then((it, result) -> is(assuming(InitState.class, it)));
    }

    @Test
    public void shouldAssumeStateForDefaultEntry() {
        given(that(machine.was(OtherTargetState.class))).willReturn(false);
        given(that(machine.go(InitState.class))).willReturn(new InitState());
        given(a(new BehaviorController(machine, conditionController)))
        .when(retrieving(the -> the.given(OtherTargetState.class)))
        .then((it, result) -> is(assuming(OtherTargetState.class, it)));
    }

    @Test
    public void shouldAssumeStateForEntry() {
        given(that(machine.was(OtherTargetState.class))).willReturn(false);
        given(that(machine.go(InitState.class))).willReturn(new InitState());
        given(a(new BehaviorController(machine, conditionController)))
        .when(retrieving(the -> the.given(OtherTargetState.DEFAULT)))
        .then((it, result) -> is(assuming(OtherTargetState.class, it)));
    }

    @Test
    public void shouldNotGoToStateForEntryAtCurrent() {
        given(that(machine.was(OtherTargetState.class))).willReturn(true);
        given(a(new BehaviorController(machine, conditionController)))
        .when(retrieving(the -> the.given(OtherTargetState.DEFAULT)))
        .then((it, result) -> is(assuming(OtherTargetState.class, it)))
        .then(theMocked(machine)).should(never()).go(OtherTargetState.class);
    }

    @Test
    public void shouldGoToStateForEntryThatDoesNotPerformStateChange() {
        given(that(machine.was(OtherTargetState.class))).willReturn(false);
        given(that(machine.go(InitState.class))).willReturn(new InitState());
        given(a(new BehaviorController(machine, conditionController)))
        .when(retrieving(the -> the.given(OtherTargetState.DEFAULT)))
        .then((it, result) -> is(assuming(OtherTargetState.class, it)))
        .then(theMocked(machine)).should().go(OtherTargetState.class);
    }

    @Test
    public void shouldNotGoToStateForEntryThatDoesPerformStateChange() {
        given(that(machine.was(OtherTargetState.class))).willReturn(false, true);
        given(that(machine.go(InitState.class))).willReturn(new InitState());
        given(a(new BehaviorController(machine, conditionController)))
        .when(retrieving(the -> the.given(OtherTargetState.DEFAULT)))
        .then((it, result) -> is(assuming(OtherTargetState.class, it)))
        .then(theMocked(machine)).should(never()).go(OtherTargetState.class);
    }

    private static class IsAssuming<T> extends TypeSafeMatcher<Assuming<T>> {

        private final Class<T> state;
        private final BehaviorController ctrl;

        public IsAssuming(final Class<T> state, final BehaviorController ctrl) {
            this.state = state;
            this.ctrl = ctrl;
        }

        @Override
        public void describeTo(final Description description) {
            description.appendText("is assuming");
        }

        @Override
        protected boolean matchesSafely(final Assuming<T> item) {
            return matchesItem(item);
        }

        private boolean matchesItem(final AbstractAssumedState<T> item) {
            return item.getState() == state && item.getController() == ctrl;
        }
    }

    static <T> IsAssuming<T> assuming(final Class<T> state, final BehaviorController ctrl) {
        return new IsAssuming<T>(state, ctrl);
    }
}
