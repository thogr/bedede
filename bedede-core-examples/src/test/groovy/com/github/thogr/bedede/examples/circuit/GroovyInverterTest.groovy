package com.github.thogr.bedede.examples.circuit

import static com.github.thogr.bedede.core.GroovyCoreExpressions.*
import static org.hamcrest.CoreMatchers.is

import org.junit.Test

class GroovyInverterTest {

    @Test
    void shouldInvertInputWhenCreatingInverterWithNoInputSignal() {
        given a: new Wire()
        and a: new Wire()
        when performing: {i, o -> new Inverter(i, o)}
        then expect: {i, o -> o.signal}, is: true
    }

    @Test
    void shouldInvertInputWhenCreatingInverterWithInputSignal() {
        given a: new Wire()
        and a: new Wire()
        with {i, o -> i.signal = true}
        when performing: {i, o -> new Inverter(i, o)}
        then expect:{i, o -> o.signal}, is: false
    }

    @Test
    public void shouldInvertInputAfterCreatingInverterWithInputSignal() {
        given a: new Wire()
        and a: new Wire()
        with {i, o -> new Inverter(i, o)}
        when performing: {i, o -> i.signal = true}
        then expect: {i, o -> o.signal}, is: false
    }

    @Test
    void shouldInvertInputAfterCreatingInverterWithNoInputSignal() {
        given a: new Wire()
        and a: new Wire()
        with {i, o -> i.signal = true}
        when performing: {i, o -> new Inverter(i, o)}
        when performing: {i, o -> i.signal = false}
        then expect: {i, o -> o.signal}, is: true
    }
}
