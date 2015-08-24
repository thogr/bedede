package com.github.thogr.bedede;

import static com.github.thogr.bedede.Bedede.a;
import static com.github.thogr.bedede.Bedede.given;
import static com.github.thogr.bedede.Bedede.retrieving;
import static com.github.thogr.bedede.mocks.MockExpressions.that;
import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.thogr.bedede.BehaviorDrivenTest.Target;
import com.github.thogr.bedede.conditions.ConditionController;

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

        public IsAssuming(Class<T> state, BehaviorController ctrl) {
            this.state = state;
            this.ctrl = ctrl;
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("is assuming");
        }

        @Override
        protected boolean matchesSafely(Assuming<T> item) {
            return item.state == state && item.getController() == ctrl;
        }
    };

    static <T> IsAssuming<T> assuming(Class<T> state, BehaviorController ctrl) {
        return new IsAssuming<T>(state, ctrl);
    }
}
