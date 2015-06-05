package com.github.thogr.bedede.examples;

import org.junit.Test;

import com.github.thogr.bedede.BehaviorDriven;
import static com.github.thogr.bedede.Bedede.*;
import static org.hamcrest.CoreMatchers.*;

public class MixedExampleTest extends BehaviorDriven {

    @Test
    public void test1() {
        given(new SystemTested())
        .then(the -> the.getState(), is(0))
        .when(performing(the->the.doSomeThing()))
        .when(transforming(it -> it.getState()))
        .then(it(), is(1));
    }

}
