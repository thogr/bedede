package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.CoreExpressions.a;
import static com.github.thogr.bedede.CoreExpressions.given;
import static com.github.thogr.bedede.CoreExpressions.it;
import static com.github.thogr.bedede.CoreExpressions.performing;
import static com.github.thogr.bedede.CoreExpressions.transforming;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class BasicExampleTest {

    @Test
    public void test1() {
        given(a(new SystemTested()))
        .then(the -> the.getState(), is(0));

        given(a(new SystemTested()))
        .when(performing(the->the.doSomeThing()))
        .when(transforming(it -> it.getState()))
        .then(it(), is(1));
    }

    @Test
    public void test2() {
        given(a(new Integer(5)))
        .then(the -> the.intValue(), is(5))
        .then(it(), is(5));
    }

    @Test
    public void test3() {
        given(a(new Integer(123)))
        .when(transforming(it -> it.toString()))
        .then(it(), is(equalTo("123")));
    }

}
