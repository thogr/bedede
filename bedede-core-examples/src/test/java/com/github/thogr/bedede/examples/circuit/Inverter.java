package com.github.thogr.bedede.examples.circuit;


public class Inverter {
    @SuppressWarnings("unused")
    private final Wire in;

    @SuppressWarnings("unused")
    private final Wire out;

    public Inverter(final Wire in, final Wire out) {
        this.in = in;
        this.out = out;
        in.addChangeListener(l -> {
            out.setSignal(!in.getSignal());
        });
    }
}
