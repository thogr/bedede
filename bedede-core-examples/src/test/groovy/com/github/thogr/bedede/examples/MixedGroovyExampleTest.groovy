package com.github.thogr.bedede.examples

import static com.github.thogr.bedede.core.CoreExpressions.*
import static org.hamcrest.CoreMatchers.is

import org.junit.Test

class MixedGroovyExampleTest {

    @Test
    void test1() {
        given(a(new SystemTested()))
        .then({it.getState()}, is(0))
    }
}
