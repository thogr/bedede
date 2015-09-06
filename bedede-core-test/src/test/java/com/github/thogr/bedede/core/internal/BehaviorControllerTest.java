package com.github.thogr.bedede.core.internal;

import static com.github.thogr.bedede.core.CoreExpressions.a;
import static com.github.thogr.bedede.core.CoreExpressions.given;
import static com.github.thogr.bedede.core.CoreExpressions.retrieving;
import static com.github.thogr.bedede.mocks.MockExpressions.that;
import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.thogr.bedede.Assuming;
import com.github.thogr.bedede.BehaviorDrivenTest.Target;

@RunWith(MockitoJUnitRunner.class)
public class BehaviorControllerTest {

    public static class TargetState {

    }

    @Mock
    private StateMachine machine;

    @Mock
    private ConditionController conditionController;

    @Test
    public void shouldAssumeTheGivenTarget() {
        given(that(machine.was(TargetState.class))).willReturn(true);
        given(a(new BehaviorController(machine, conditionController)))
        .when(retrieving(the -> the.given(Target.class)))
        .then((it, result) -> is(assuming(Target.class, it)));
    }

    private static class IsAssuming<T> extends TypeSafeMatcher<Assuming<T>> {

        private Class<T> state;
        private BehaviorController ctrl;

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
