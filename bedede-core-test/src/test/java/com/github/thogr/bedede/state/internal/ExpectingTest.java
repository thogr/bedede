package com.github.thogr.bedede.state.internal;

import static com.github.thogr.bedede.CoreExpressions.an;
import static com.github.thogr.bedede.CoreExpressions.given;
import static com.github.thogr.bedede.CoreExpressions.performing;
import static com.github.thogr.bedede.CoreExpressions.then;
import static com.github.thogr.bedede.CoreExpressions.transforming;
import static com.github.thogr.bedede.MockitoExpressions.theMocked;
import static com.github.thogr.bedede.state.StateExpressions.expecting;
import static com.github.thogr.bedede.state.StateExpressions.otherwise;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.internal.ConditionVerifier;

@RunWith(MockitoJUnitRunner.class)
public class ExpectingTest {

    @Mock
    private ConditionVerifier<BooleanCondition> verifier;

    @Test
    public void shouldVerifyBothOperands() {
        given(an(expecting(true, otherwise("left!"))))
        .and(an(expecting(false, otherwise("right!"))))
        .when(transforming((first, second)->first.and(second)))
        .when(performing(result -> {
            final Verifiable<BooleanCondition> it = result;
            it.verify(verifier);
        }));
        then(theMocked(verifier)).should().verify(anyObject(), eq(otherwise("left!")));
        then(theMocked(verifier)).should().verify(anyObject(), eq(otherwise("right!")));

    }
}
