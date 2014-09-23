package com.github.thogr.bedede.state;

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

	public String toString() {
		return getName() + " = " + getValue();
	}
}
