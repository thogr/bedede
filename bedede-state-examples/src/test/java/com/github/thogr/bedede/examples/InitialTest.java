package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.StateExpressions.at;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;

public class InitialTest extends BehaviorDriven {

    @Test
    public void exampleGivenThenExpectingExpression() throws Exception {
        given(at(Initial.class))
        .then(it -> it.hasState(1));
    }
}


