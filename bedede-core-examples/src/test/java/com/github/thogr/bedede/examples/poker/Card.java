package com.github.thogr.bedede.examples.poker;

public class Card implements Comparable<Card>{

    private Rank rank;
    private Suite suite;

    public Card(Rank rank, Suite suite) {
        this.rank = rank;
        this.suite = suite;
    }

    public enum Rank {
        ONE("1"), TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"),
        EIGHT("8"), NINE("9"), TEN("10"), JACKIE("J"), QUEEN("Q"), KINK("K");

        private String str;

        Rank(String str) {
            this.str = str;
        }

        static Rank valueFrom(String str) {
            for (Rank r : Rank.values()) {
                if (r.str.equals(str)) return r;
            }
            throw new IllegalArgumentException("Unknown Rank " + str);
        }

        public String toString() {
            return str;
        }
    }

    public enum Suite {
        HEARTS("H"), SPADES("S"), CLUBS("C"), DIAMONDS("D");

        private String str;

        Suite(String str) {
            this.str = str;
        }

        static Suite valueFrom(String str) {
            for (Suite r : Suite.values()) {
                if (r.str.equals(str)) return r;
            }
            throw new IllegalArgumentException("Unknown Suite " + str);
        }

        public String toString() {
            return str;
        }
    }

    public Card(String code) {
        if (!code.startsWith("10")) {
            rank = Rank.valueFrom("" + code.charAt(0));
            suite = Suite.valueFrom("" + code.charAt(1));
        } else {
            rank = Rank.valueFrom("10");
            suite = Suite.valueFrom(code.substring(2));
        }
    }

    @Override
    public int compareTo(Card other) {
        Card otherCard = (Card) other;
        return rank.compareTo(otherCard.rank);
    }

    public String toString() {
        return rank.toString() + suite.toString();
    }
}
