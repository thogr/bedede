// CHECKSTYLE:OFF MagicNumber

package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.CoreExpressions.a;
import static com.github.thogr.bedede.CoreExpressions.given;
import static com.github.thogr.bedede.CoreExpressions.it;
import static com.github.thogr.bedede.CoreExpressions.performing;
import static com.github.thogr.bedede.CoreExpressions.retrieving;
import static com.github.thogr.bedede.CoreExpressions.transforming;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.function.BiFunction;

import org.junit.Test;

public class BehaviorExpressionExampleTest {

    public static class Incrementable {
        private int value = 0;

        public void increment() {
            incrementBy(1);
        }

        public void incrementBy(final int i) {
            value += i;
        }

        public int getValue() {
            return value;
        }
    }

    public static class Person {
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

        private String getFirstName() {
            return firstName;
        }

        private String getFamilyName() {
            return familyName;
        }
    }

    @Test
    public void testName() throws Exception {
        given(a(new Person())).with(it -> {
            it.setFirstName("John");
            it.setFamilyName("Smith");
        })
        .when(retrieving(Person::getFullName))
        .then(it(), is("John Smith"));
    }

    @Test
    public void testNameAgain() throws Exception {
        given(a(new Person())).with(it -> {
            it.setFirstName("John");
            it.setFamilyName("Smith");
        })
        .then(Person::getFullName, is("John Smith"));
    }

    @Test
    public void testNameWithTwo() throws Exception {
        given(a(new Person())).and(a(new Person())).with((p1, p2) -> {
            p1.setFirstName("John");
            p1.setFamilyName("Smith");

            p2.setFirstName("Jane");
            p2.setFamilyName("Jones");
        })
        .when(performing((p1, p2) -> p1.setFamilyName(p2.getFamilyName())))
        .then((p1, p2) -> p2.getFullName(), is("Jane Jones"))
        .then(p1 -> p1.getFullName(), is("John Jones"));
    }

    @Test
    public void testNameWithTransformedBehavior() throws Exception {
        given(a(new Person())).with(it -> {
            it.setFirstName("John");
            it.setFamilyName("Smith");
        })
        .when(retrieving(Person::getFullName))
        .then((the, fullname) -> is(the.getFirstName() + " " + the.getFamilyName()));
    }

    @Test
    public void testNameWithTransformedBehavior2() throws Exception {
        given(a(new Person())).with(it -> {
            it.setFirstName("John");
            it.setFamilyName("Smith");
        })
        .when(retrieving(Person::getFullName))
        .then((it, name) -> is("John Smith"));
    }

    @Test
    public void testNameWithTwoTricky() throws Exception {
        given(a(new Person())).and(a(new Person())).with(p2 -> {
            p2.setFirstName("John");
            p2.setFamilyName("Smith");
        })
        .when(performing((p1, p2) -> p1.setFirstName("Jack")))
        .when(performing(p -> p.setFamilyName("Jones")))
        .then(p -> p.getFullName(), is("Jack Jones"));
    }

    @Test
    public void testName1() throws Exception {
        given(a(new Runnable() {
            @Override
            public void run() {
            }}))
        .when(performing(it-> it.run()));
    }

    @Test
    public void testName2a() throws Exception {
        given(a(new Integer(123)))
        .when(transforming(it -> it.toString()))
        .then(it -> it, is(equalTo("123")));
    }

    @Test
    public void testName2b() throws Exception {
        given(a(new Integer(123)))
        .when(transforming(it -> it.toString()))
        .then(it(), is(equalTo("123")));
    }

    @Test
    public void testName2c() throws Exception {
        given(a(new Integer(123)))
        .then(it -> it.toString(), is(equalTo("123")));
    }

    @Test
    public void testName3() throws Exception {
        given(a(new Integer(8)))
        .then(the -> the.hashCode(), is(equalTo(8)));
    }

    @Test
    public void testName4() throws Exception {
        given(a(new Integer(123)))
        .when(transforming(it -> it.toString()))
        .then(the -> the.hashCode(), is(equalTo(48690)));
    }

    @Test
    public void testName5() throws Exception {
        given(a(new Incrementable()))
        .when(performing(it->it.incrementBy(2)))
        .when(performing(it->it.incrementBy(3)))
        .then(the -> the.getValue(), is(equalTo(5)));
    }

    @Test
    public void testName6() throws Exception {
        given(a("abc"))
        .then(the -> the.length(), is(equalTo(3)))
        .then(it -> it.toUpperCase(), is(equalTo("ABC")))
        .then(it(), is(equalTo("abc")));

        given(a("abc"))
        .when(transforming(it -> it.toUpperCase() + "123"))
        .then(it(), is(equalTo("ABC123")));
    }

    @Test
    public void testName7() throws Exception {
        given(a("abc"))
        .when(transforming(it -> it.toUpperCase()))
        .then(it(), is(equalTo("ABC")))
        .then(String::length, is(3));
    }

    @Test
    public void testName8() throws Exception {
        given(a(new Incrementable()))
        .when(performing(the -> the.increment())).twice()
        .then(Incrementable::getValue, is(equalTo(2)));
    }

    @Test
    public void testName9() throws Exception {
        given(a("    "))
        .when(transforming(it -> it.trim()))
        .then(it -> it.isEmpty());
    }

    @Test
    public void testName10() throws Exception {
        given(a(new StringBuffer())).and(a("me"))
        .when(transforming((s, a) -> s.append(a)))
        .then(the->the.toString(), is("me"));
    }

    @Test
    public void testName11() throws Exception {
        given(a(new StringBuffer())).and(a("me"))
        .when(performing((s, a) -> s.append(a)))
        .then(the->the.toString(), is("me"));
    }

    @Test
    public void testName12() throws Exception {
        given(a(new HashMap<String, Integer>())).and(a("me"))
        .when(performing((s, a) -> s.put(a, 123)))
        .then(the->the.get("me"), is(123));
    }

    @Test
    public void testName13() throws Exception {
        given(a(new HashSet<String>())).and(a(Arrays.asList("a", "b", "c")))
        .when(performing((s, l) -> s.addAll(l)))
        .then(it(), contains("a", "b", "c"));
    }

    @Test
    public void testName14() throws Exception {
        given(a(new HashSet<String>())).and(a(Arrays.asList("a", "b", "c")))
        .when(transforming((s, l) -> s.addAll(l)))
        .then(it(), is(true));
    }

    @Test
    public void testName15() throws Exception {
        final BiFunction<HashSet<String>, List<String>, Boolean> func = (s, l) -> s.addAll(l);

        given(a(new HashSet<String>())).and(a(Arrays.asList("a", "b", "c")))
        .when(transforming(func::apply))
        .then(it(), is(true));
    }

    @Test
    public void testName16() throws Exception {
        given(a(new StringBuffer("me")))
        .when(transforming(StringBuffer::toString))
        .then(it(), is("me"));
    }
}
