package com.github.thogr.bedede.conditions;

import static com.github.thogr.bedede.core.CoreExpressions.an;
import static com.github.thogr.bedede.core.CoreExpressions.expecting;
import static com.github.thogr.bedede.core.CoreExpressions.given;
import static com.github.thogr.bedede.core.CoreExpressions.otherwise;
import static com.github.thogr.bedede.core.CoreExpressions.performing;
import static com.github.thogr.bedede.core.CoreExpressions.transforming;
import static org.mockito.BDDMockito.then;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.eq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;


@RunWith(MockitoJUnitRunner.class)
public class ExpectingTest {

    @Mock
    private ConditionVerifier<BooleanCondition> verifier;

    @Test
    public void shouldVerifyBothOperands() {
        given(an(expecting(true, otherwise("left!"))))
        .and(an(expecting(false, otherwise("right!"))))
        .when(transforming((a, b)->a.and(b)))
        .when(performing(the -> the.verify(verifier)));
        then(verifier).should().verify(anyObject(), eq(otherwise("left!")));
        then(verifier).should().verify(anyObject(), eq(otherwise("right!")));

    }
}
