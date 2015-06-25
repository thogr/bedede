package com.github.thogr.bedede.test;

import org.junit.Test;

import com.github.thogr.bedede.Bedede;
import com.github.thogr.bedede.BehaviorDriven;

import static org.hamcrest.CoreMatchers.*;

public class MixedTest extends BehaviorDriven {

    @Test
    public void shouldHaveAccessToBehaviorExpressions() throws Exception {
        given("A String")
        .then(the->the.length(), is(8));
    }

}
