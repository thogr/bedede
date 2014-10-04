package com.github.thogr.bedede.examples;

public class SystemTested {

    private static int state = 0;

    public static int getState() {
        return state;
    }

    public static void doSomeThing() {
        state = 1;
    }

}
