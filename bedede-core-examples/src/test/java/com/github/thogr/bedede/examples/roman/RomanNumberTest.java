package com.github.thogr.bedede.examples.roman;

import org.junit.Test;
import static com.github.thogr.bedede.Bedede.*;
import static org.hamcrest.CoreMatchers.*;
public class RomanNumberTest {

    @Test
    public void shouldGetOneForI() {
        given(new RomanNumber("I"))
        .then(it -> it.toInteger(), is(1));
    }

    @Test
    public void shouldGet5ForV() {
        given(new RomanNumber("V"))
        .then(it -> it.toInteger(), is(5));
    }

    @Test
    public void shouldGet10ForX() {
        given(new RomanNumber("X"))
        .then(it -> it.toInteger(), is(10));
    }

    @Test
    public void shouldGet11ForXI() {
        given(new RomanNumber("XI"))
        .then(it -> it.toInteger(), is(11));
    }

    @Test
    public void shouldGet12ForXII() {
        given(new RomanNumber("XII"))
        .then(it -> it.toInteger(), is(12));
    }

    @Test
    public void shouldGet4ForIV() {
        given(new RomanNumber("IV"))
        .then(it -> it.toInteger(), is(4));
    }

    @Test
    public void shouldGet14ForXIV() {
        given(new RomanNumber("XIV"))
        .then(it -> it.toInteger(), is(14));
    }

    @Test
    public void shouldGet24ForXXIV() {
        given(new RomanNumber("XXIV"))
        .then(it -> it.toInteger(), is(24));
    }

    @Test
    public void shouldGet2414ForMMCDXIV() {
        given(new RomanNumber("MMCDXIV"))
        .then(it -> it.toInteger(), is(2414));
    }
}
