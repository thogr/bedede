package com.github.thogr.bedede.examples.circuit;

import static com.github.thogr.bedede.Bedede.given;
import static com.github.thogr.bedede.Bedede.performing;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class InverterTest {

    @Test
    public void shouldInvertInputWhenCreatingInverterWithNoInputSignal() {
        given(new Wire()).and(new Wire())
        .when(performing(Inverter::new))
        .then((in, out) -> out.getSignal(), is(true));
    }

    @Test
    public void shouldInvertInputWhenCreatingInverterWithInputSignal() {
        given(new Wire()).and(new Wire()).with((in, out) -> {
            in.setSignal(true);
        })
        .when(performing(Inverter::new))
        .then((in, out) -> out.getSignal(), is(false));
    }

    @Test
    public void shouldInvertInputAfterCreatingInverterWithInputSignal() {
        given(new Wire()).and(new Wire())
        .when(performing(Inverter::new))
        .when(performing((in, out) -> in.setSignal(true)))
        .then((in, out) -> out.getSignal(), is(false));
    }

    @Test
    public void shouldInvertInputAfterCreatingInverterWithNoInputSignal() {
        given(new Wire()).and(new Wire()).with((in, out) -> {
            in.setSignal(true);
        })
        .when(performing(Inverter::new))
        .when(performing((in, out) -> in.setSignal(false)))
        .then((in, out) -> out.getSignal(), is(true));
    }
}
