package com.github.thogr.bedede;

public class Parameter {

    private final String name;
    private final String value;

    public Parameter(final String name, final String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Parameter [" + getName() + " = " + getValue() + "]";
    }
}
