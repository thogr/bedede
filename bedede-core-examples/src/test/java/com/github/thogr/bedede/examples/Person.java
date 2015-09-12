package com.github.thogr.bedede.examples;

public class Person {
    private String firstName = "";
    private String familyName = "";

    public void setFirstName(final String name) {
        this.firstName = name;
    }

    public void setFamilyName(final String name) {
        this.familyName = name;
    }

    public String getFullName() {
        return firstName + " " + familyName;
    }

    String getFirstName() {
        return firstName;
    }

    String getFamilyName() {
        return familyName;
    }
}
