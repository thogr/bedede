package com.github.thogr.bedede.examples;

import static org.hamcrest.CoreMatchers.*;
import org.junit.Test;
import static com.github.thogr.bedede.Bedede.*;

public class BehaviorExpressionExampleTest {

    class Incrementable {
        private int value = 0;
        void incrementBy(int i) {
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
        .when(performing(it-> it.run()));
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
        .when(performing(the->the.incrementBy(2)))
        .when(performing(the->the.incrementBy(3)))
        .then(the -> the.getValue(), is(equalTo(5)));
    }

    @Test
    public void testName6() throws Exception {
        given("abc")
        .then(the -> the.length(), is(equalTo(3)))
        .then(it -> it.toUpperCase(), is(equalTo("ABC")))
        .then(it(), is(equalTo("abc")))
        .when(transforming(it -> it.toUpperCase() + "123"))
        .then(it(), is(equalTo("ABC123")));
    }
}
