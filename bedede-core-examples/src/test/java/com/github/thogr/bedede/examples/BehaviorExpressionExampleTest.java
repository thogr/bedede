package com.github.thogr.bedede.examples;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;

import static com.github.thogr.bedede.Bedede.*;

public class BehaviorExpressionExampleTest {

    public static class Incrementable {
        private int value = 0;
        public void increment() {
            incrementBy(1);
        }
        public void incrementBy(int i) {
            value += i;
        }
        public int getValue() {
            return value;
        }
    }

    @Test
    public void testName1() throws Exception {
        given(new Runnable(){
            @Override
            public void run() {
            }})
        .when(it-> it.run());
    }

    @Test
    public void testName2a() throws Exception {
        given(new Integer(123))
        .when(transforming(it -> it.toString()))
        .then(it -> it, is(equalTo("123")));
    }

    @Test
    public void testName2b() throws Exception {
        given(new Integer(123))
        .when(transforming(it -> it.toString()))
        .then(it(), is(equalTo("123")));
    }

    @Test
    public void testName2c() throws Exception {
        given(new Integer(123))
        .then(it -> it.toString(), is(equalTo("123")));
    }

    @Test
    public void testName3() throws Exception {
        given(new Integer(8))
        .then(the -> the.hashCode(), is(equalTo(8)));
    }

    @Test
    public void testName4() throws Exception {
        given(new Integer(123))
        .when(transforming(it -> it.toString()))
        .then(the -> the.hashCode(), is(equalTo(48690)));
    }

    @Test
    public void testName5() throws Exception {
        given(new Incrementable())
        .when(it->it.incrementBy(2))
        .when(it->it.incrementBy(3))
        .then(the -> the.getValue(), is(equalTo(5)));
    }

    @Test
    public void testName6() throws Exception {
        given("abc")
        .then(the -> the.length(), is(equalTo(3)))
        .then(it -> it.toUpperCase(), is(equalTo("ABC")))
        .then(it(), is(equalTo("abc")));

        given("abc")
        .when(transforming(it -> it.toUpperCase() + "123"))
        .then(it(), is(equalTo("ABC123")));
    }

    @Test
    public void testName7() throws Exception {
        given("abc")
        .when(transforming(it("toUpperCase")))
        .then(it(), is(equalTo("ABC")))
        .then(the("length"), is(3));
    }

    @Test
    public void testName8() throws Exception {
        given(new Incrementable())
        .when(performing(theAction("increment"))).twice()
        .then(the("getValue"), is(equalTo(2)));
    }

    @Test
    public void testName9() throws Exception {
        given("    ")
        .when(transforming(it -> it.trim()))
        .then(it -> it.isEmpty());
    }
}
