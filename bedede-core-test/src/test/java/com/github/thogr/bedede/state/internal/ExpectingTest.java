package com.github.thogr.bedede.state.internal;

import static com.github.thogr.bedede.core.CoreExpressions.an;
import static com.github.thogr.bedede.core.CoreExpressions.given;
import static com.github.thogr.bedede.core.CoreExpressions.otherwise;
import static com.github.thogr.bedede.core.CoreExpressions.performing;
import static com.github.thogr.bedede.core.CoreExpressions.then;
import static com.github.thogr.bedede.core.CoreExpressions.transforming;
import static com.github.thogr.bedede.mocks.MockExpressions.theMocked;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.ConditionVerifier;
import com.github.thogr.bedede.state.StateExpressions;


@RunWith(MockitoJUnitRunner.class)
public class ExpectingTest {

    @Mock
    private ConditionVerifier<BooleanCondition> verifier;

    @Test
    public void shouldVerifyBothOperands() {
        given(an(StateExpressions.expecting(true, otherwise("left!"))))
        .and(an(StateExpressions.expecting(false, otherwise("right!"))))
        .when(transforming((first, second)->first.and(second)))
        .when(performing(result -> {
            final Verifiable<BooleanCondition> it = result;
            it.verify(verifier);
        }));
        then(theMocked(verifier)).should().verify(anyObject(), eq(otherwise("left!")));
        then(theMocked(verifier)).should().verify(anyObject(), eq(otherwise("right!")));

    }
}
