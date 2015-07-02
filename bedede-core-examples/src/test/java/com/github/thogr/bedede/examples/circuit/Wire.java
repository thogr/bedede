package com.github.thogr.bedede.examples.circuit;

import java.util.ArrayList;
import java.util.List;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Wire {

    private boolean signal;
    private List<ChangeListener> listeners = new ArrayList<>();

    public void setSignal(boolean signal) {
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

    public void addChangeListener(ChangeListener listener) {
        listeners.add(listener);
        fireStateChanged();
    }
}
