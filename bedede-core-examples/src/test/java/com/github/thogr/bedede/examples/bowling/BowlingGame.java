// CHECKSTYLE:OFF MagicNumber

package com.github.thogr.bedede.examples.bowling;

public class BowlingGame {
    private int[] rolls = new int[21];
    private int current = 0;

    public void roll(final int pins) {
        rolls[current++] = pins;
    }

    public int score() {
        int total = 0;
        int frameIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex)) {
                total += 10 + rolls[frameIndex + 1] + rolls[frameIndex + 2];
                frameIndex += 1;
            } else if (isSpare(frameIndex)) {
                total += 10 + rolls[frameIndex + 2];
                frameIndex += 2;
            } else {
                total += rolls[frameIndex] + rolls[frameIndex + 1];
                frameIndex += 2;
            }
        }
        return total;
    }

    private boolean isStrike(final int frameIndex) {
        return rolls[frameIndex] == 10;
    }

    private boolean isSpare(final int frameIndex) {
        return rolls[frameIndex] + rolls[frameIndex + 1] == 10;
    }
}
