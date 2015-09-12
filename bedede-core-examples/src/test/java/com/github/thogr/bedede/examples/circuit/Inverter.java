package com.github.thogr.bedede.examples.circuit;


public class Inverter {
    @SuppressWarnings("unused")
    private final Wire in;

    @SuppressWarnings("unused")
    private final Wire out;

    /**
     * Creates an inverter with two wires, the input and the output wires
     * @param in the input wire
     * @param out the output wire
     */
    public Inverter(final Wire in, final Wire out) {
        this.in = in;
        this.out = out;
        in.addChangeListener(l -> {
            out.setSignal(!in.getSignal());
        });
    }
}
