package com.github.thogr.bedede.examples;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;

public class InitialTest extends BehaviorDriven {

    @Test
    public void exampleGivenThenExpect() throws Exception {
        given(Initial.class)
        .then()
        .expect(it -> it.hasState(1));
    }
}


