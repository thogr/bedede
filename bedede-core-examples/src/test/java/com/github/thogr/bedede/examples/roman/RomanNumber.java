// CHECKSTYLE:OFF MagicNumber

package com.github.thogr.bedede.examples.roman;

import java.util.HashMap;
import java.util.Map;

public class RomanNumber {

    private String value;
    private final Map<Character, Integer> digits = new HashMap<>();

    private static final String ROMAN_DIGITS = "IVXLCDM";
    private static final int[] DECIMALS = {1, 5, 10, 50, 100, 500, 1000};

    /**
     * Creates a RomanNumber
     */
    public RomanNumber() {
        for (int i = 0; i < DECIMALS.length; i++) {
            digits.put(ROMAN_DIGITS.charAt(i), DECIMALS[i]);
        }
    }

    RomanNumber(final String value) {
        this();
        this.value = value;
    }

    /**
     * Converts the RomanNumber to integer
     * @return the integer
     */
    public int toInteger() {
        int result = 0;
        int i = 0;
        do {
            final Integer current = getDigitAt(i);
            if (i + 1 < value.length()) {
                final Integer next = getDigitAt(i + 1);
                if (current < next) {
                    result += next - current;
                    i++;
                } else {
                    result += current;
                }
            } else {
                result += current;
            }
        } while (++i < value.length());

        return result;
    }

    private Integer getDigitAt(final int i) {
        return digits.get(value.charAt(i));
    }
}
