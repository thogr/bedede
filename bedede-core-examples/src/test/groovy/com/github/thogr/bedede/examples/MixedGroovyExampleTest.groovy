package com.github.thogr.bedede.examples

import static com.github.thogr.bedede.Bedede.it;
import static com.github.thogr.bedede.Bedede.performing;
import static com.github.thogr.bedede.Bedede.transforming;
import static org.hamcrest.CoreMatchers.is;
import static com.github.thogr.bedede.Bedede.*

import org.junit.Test;

class MixedGroovyExampleTest {

    @Test
    void test1() {
        given(new SystemTested())
        .then({it.getState()}, is(0))
    }
}
