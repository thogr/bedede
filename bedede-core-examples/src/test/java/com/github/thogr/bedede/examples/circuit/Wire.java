package com.github.thogr.bedede.examples.circuit;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Wire {

    private boolean signal;
    private final List<ChangeListener> listeners = new ArrayList<>();

    public void setSignal(final boolean signal) {
        this.signal = signal;
        fireStateChanged();
    }

    private void fireStateChanged() {
        listeners.forEach(listener -> {
            listener.stateChanged(new ChangeEvent(this));
        });
    }

    public boolean getSignal() {
        return signal;
    }

    /**
     * Add a ChangeListener to listen for state changes
     * @param listener the change listener
     */
    public void addChangeListener(final ChangeListener listener) {
        listeners.add(listener);
        fireStateChanged();
    }
}
