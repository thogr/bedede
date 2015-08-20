package com.github.thogr.bedede.conditions;

import static com.github.thogr.bedede.Bedede.expecting;
import static com.github.thogr.bedede.Bedede.given;
import static com.github.thogr.bedede.Bedede.otherwise;
import static com.github.thogr.bedede.Bedede.performing;
import static com.github.thogr.bedede.Bedede.transforming;
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
        given(expecting(true, otherwise("left!")))
        .and(expecting(false, otherwise("right!")))
        .when(transforming((a, b)->a.and(b)))
        .when(performing(the -> the.verify(verifier)));
        then(verifier).should().verify(anyObject(), eq(otherwise("left!")));
        then(verifier).should().verify(anyObject(), eq(otherwise("right!")));

    }
}
