package com.github.thogr.bedede.test;

import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;

public class MixedTest extends BehaviorDriven {

    @Test
    public void shouldHaveAccessToBehaviorExpressions() throws Exception {
        given("A String")
        .then(the->the.length(), is(8));
    }

}
