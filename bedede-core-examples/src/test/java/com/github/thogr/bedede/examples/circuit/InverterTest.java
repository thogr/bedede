package com.github.thogr.bedede.examples.circuit;

import static com.github.thogr.bedede.CoreExpressions.a;
import static com.github.thogr.bedede.CoreExpressions.given;
import static com.github.thogr.bedede.CoreExpressions.performing;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;

public class InverterTest {

    @Test
    public void shouldInvertInputWhenCreatingInverterWithNoInputSignal() {
        given(a(new Wire())).and(a(new Wire()))
        .when(performing(Inverter::new))
        .then((in, out) -> out.getSignal(), is(true));
    }

    @Test
    public void shouldInvertInputWhenCreatingInverterWithInputSignal() {
        given(a(new Wire())).and(a(new Wire())).with((in, out) -> {
            in.setSignal(true);
        })
        .when(performing(Inverter::new))
        .then((in, out) -> out.getSignal(), is(false));
    }

    @Test
    public void shouldInvertInputAfterCreatingInverterWithInputSignal() {
        given(a(new Wire())).and(a(new Wire())).with(Inverter::new)
        .when(performing((in, out) -> in.setSignal(true)))
        .then((in, out) -> out.getSignal(), is(false));
    }

    @Test
    public void shouldInvertInputAfterCreatingInverterWithNoInputSignal() {
        given(a(new Wire())).and(a(new Wire())).with((in, out) -> {
            in.setSignal(true);
        })
        .when(performing(Inverter::new))
        .when(performing((in, out) -> in.setSignal(false)))
        .then((in, out) -> out.getSignal(), is(true));
    }

    @Test
    public void shouldInvertNestedExample() {
        given(a(new Wire()).with(it ->
            it.setSignal(true)
        ), in ->
        given(a(new Wire()))
        .when(performing(out -> new Inverter(in, out)))
        .when(performing(the -> in.setSignal(false)))
        .then(out -> out.getSignal(), is(true)));
    }
}
