package com.github.thogr.bedede.examples;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;

public class InitialTest extends BehaviorDriven {

    @Test
    public void exampleGivenThenShould() throws Exception {
        given(Initial.class)
        .then()
        .should(it -> it.expectState(1));
    }
}


