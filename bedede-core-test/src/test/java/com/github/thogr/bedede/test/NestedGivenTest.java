package com.github.thogr.bedede.test;

import static com.github.thogr.bedede.CoreExpressions.a;
import static com.github.thogr.bedede.CoreExpressions.given;
import static com.github.thogr.bedede.CoreExpressions.performing;
import static com.github.thogr.bedede.CoreExpressions.then;
import static com.github.thogr.bedede.CoreExpressions.transforming;
import static com.github.thogr.bedede.CoreExpressions.when;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;

import java.util.Vector;

import org.junit.Test;

public class NestedGivenTest {

    @Test(expected = IllegalArgumentException.class)
    public void illegalWhenPerforming() throws Exception {
        when(performing(the -> new Vector<String>()));
    }

    @Test
    public void correctNestedWhenPerforming() throws Exception {
        given(a("string"), string ->
        given(a(new StringBuffer(string)), buffer ->
        when(performing(the -> buffer.append(string)))
        .then(it -> it.toString(), is("stringstring"))));
    }

    @Test(expected = IllegalArgumentException.class)
    public void illegalWhenTransforming() throws Exception {
        when(transforming(it -> new Vector<String>()));
    }

    @Test
    public void correctNestedWhenTransforming() throws Exception {
        given(a("string"), string ->
        when(transforming(it -> new StringBuffer(string)))
        .then(it -> it.toString(), is("string")));
    }

    @Test
    public void nestedThen() throws Exception {
        given(a("string"), string ->
        then(string, equalTo("string")));
    }

    @Test
    public void itShouldHaveCorrectTypeWhenPerforming() throws Exception {
        given(a(new Vector<String>()), v1 ->
        when(performing(the -> v1.add("Carl")))
        .then(it -> it.getClass(), is(equalTo(Vector.class))));

    }

    @Test
    public void itShouldHaveCorrectTypeWhenTransforming() throws Exception {
        given(a(new Vector<String>()).with(it -> it.add("Carl")), v1 ->
        when(transforming(the -> v1.elementAt(0)))
        .then(it -> it.getClass(), is(equalTo(String.class))));
    }
}
