package com.github.thogr.bedede.examples;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;

public class InitialTest extends BehaviorDriven {

    @Test
    public void testName() throws Exception {
        given(Initial.class);
        then(expecting(itsTrue(), otherwise("it's false")));
    }

    private MyCondition<Boolean> itsTrue() {
        return () -> true;
    }
}
