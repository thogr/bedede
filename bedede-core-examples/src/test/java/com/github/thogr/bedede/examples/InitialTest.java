package com.github.thogr.bedede.examples;

import org.junit.Test;

import com.github.thogr.bedede.core.BehaviorDriven;

public class InitialTest extends BehaviorDriven {

    @Test
    public void exampleGivenThenExpectingExpression() throws Exception {
        given(Initial.class)
        .then(it -> it.hasState(1));
    }
}


