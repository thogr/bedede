package com.github.thogr.bedede.examples.roman;

import java.util.HashMap;
import java.util.Map;

public class RomanCalculator {

    private Map<String, Integer> digits = new HashMap<>();

    public RomanCalculator() {
        digits.put("I", 1);
        digits.put("V", 5);
        digits.put("X", 10);
    }

    public int toInteger(String string) {
        int result = 0;
        for (char c : string.toCharArray()) {
            Integer digit = digits.get("" + c);
            result += digit != null ? digit : 0;
        }
        return result;
    }
}
