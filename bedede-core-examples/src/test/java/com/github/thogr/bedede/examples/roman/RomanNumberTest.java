package com.github.thogr.bedede.examples.roman;

import static com.github.thogr.bedede.CoreExpressions.a;
import static com.github.thogr.bedede.CoreExpressions.given;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
public class RomanNumberTest {

    @Test
    public void shouldGetOneForI() {
        given(a(new RomanNumber("I")))
        .then(it -> it.toInteger(), is(1));
    }

    @Test
    public void shouldGet5ForV() {
        given(a(new RomanNumber("V")))
        .then(it -> it.toInteger(), is(5));
    }

    @Test
    public void shouldGet10ForX() {
        given(a(new RomanNumber("X")))
        .then(it -> it.toInteger(), is(10));
    }

    @Test
    public void shouldGet11ForXI() {
        given(a(new RomanNumber("XI")))
        .then(it -> it.toInteger(), is(11));
    }

    @Test
    public void shouldGet12ForXII() {
        given(a(new RomanNumber("XII")))
        .then(it -> it.toInteger(), is(12));
    }

    @Test
    public void shouldGet4ForIV() {
        given(a(new RomanNumber("IV")))
        .then(it -> it.toInteger(), is(4));
    }

    @Test
    public void shouldGet14ForXIV() {
        given(a(new RomanNumber("XIV")))
        .then(it -> it.toInteger(), is(14));
    }

    @Test
    public void shouldGet24ForXXIV() {
        given(a(new RomanNumber("XXIV")))
        .then(it -> it.toInteger(), is(24));
    }

    @Test
    public void shouldGet2414ForMMCDXIV() {
        given(a(new RomanNumber("MMCDXIV")))
        .then(it -> it.toInteger(), is(2414));
    }
}
