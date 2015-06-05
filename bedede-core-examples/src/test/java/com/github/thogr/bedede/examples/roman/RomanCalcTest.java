package com.github.thogr.bedede.examples.roman;

import org.junit.Test;
import static com.github.thogr.bedede.Bedede.*;
import static org.hamcrest.CoreMatchers.*;
public class RomanCalcTest {

    @Test
    public void shouldGetOneForI() {
        given(new RomanCalculator())
        .then(it -> it.toInteger("I"), is(1));
    }

    @Test
    public void shouldGetFiveForV() {
        given(new RomanCalculator())
        .then(it -> it.toInteger("V"), is(5));
    }

    @Test
    public void shouldGetTenForX() {
        given(new RomanCalculator())
        .then(it -> it.toInteger("X"), is(10));
    }

    @Test
    public void shouldGetElevenForXI() {
        given(new RomanCalculator())
        .then(it -> it.toInteger("XI"), is(11));
    }

    @Test
    public void shouldGetTwelveForXII() {
        given(new RomanCalculator())
        .then(it -> it.toInteger("XII"), is(12));
    }
}
