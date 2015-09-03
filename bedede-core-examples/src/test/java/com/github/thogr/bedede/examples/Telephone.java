package com.github.thogr.bedede.examples;

import java.util.ArrayList;
import java.util.List;

public class Telephone {

    public enum Tone {
        SILENT,
        DIAL_TONE,
        CALL_TONE
    }

    private Tone tone = null;
    private List<Integer> dialed = new ArrayList<>();

    public String getDialedNumber() {
        final StringBuilder builder = new StringBuilder();
        for (final Integer digit : dialed) {
            builder.append(digit.toString());
        }
        return builder.toString();
    }

    private String[] phoneNumbers = {
      "112",
      "555123456",
      "555101010"
    };

    public void pickUp() {
        tone = Tone.DIAL_TONE;
    }

    public void hangUp() {
        tone = null;
        dialed = new ArrayList<>();
    }

    public void pressKey(final int key) {
        dialed.add(key);
        if (isDialedClientNumber()) {
            tone = Tone.CALL_TONE;
        } else {
            tone = Tone.SILENT;
        }
    }

    private boolean isDialedClientNumber() {
        final String dialedNumber = getDialedNumber();
        for (final String number : phoneNumbers) {
            if (number.equals(dialedNumber)) {
                return true;
            }
        }
        return false;
    }

    public Tone getTone() {
        return tone;
    }

}
