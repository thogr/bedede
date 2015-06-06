package com.github.thogr.bedede.examples.roman;

import java.util.HashMap;
import java.util.Map;

public class RomanNumber {

    private String value;
    private Map<Character, Integer> digits = new HashMap<>();

    private static String ROMAN_DIGITS = "IVXLCDM";
    private static int[] DECIMALS = {1, 5, 10, 50, 100, 500, 1000};

    public RomanNumber() {
        for (int i = 0; i < DECIMALS.length; i++) {
            digits.put(ROMAN_DIGITS.charAt(i), DECIMALS[i]);
        }
    }

    RomanNumber(String value) {
        this();
        this.value = value;
    }

    public int toInteger() {
        int result = 0;
        int i = 0;
        do {
            Integer current = getDigitAt(i);
            if (i+1 < value.length()) {
                Integer next = getDigitAt(i+1);
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

    private Integer getDigitAt(int i) {
        return digits.get(value.charAt(i));
    }
}
