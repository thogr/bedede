package com.github.thogr.bedede;

import static com.github.thogr.bedede.Expressions.given;
import static com.github.thogr.bedede.Expressions.it;
import static com.github.thogr.bedede.Expressions.retrieving;
import static java.util.stream.Collectors.toList;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Test;

import com.github.thogr.bedede.core.CoreExpressions;
import com.github.thogr.bedede.mocks.MockExpressions;

public class ExpressionsTest {

    @Test
    public void shouldHaveAllMethods() {
        given().the(Expressions.class)
        .when(retrieving(ExpressionsTest::allPublicStaticMethods))
        .then(it(), containsAllMethodsIn(allPublicStaticMethods(MockExpressions.class)))
        .then(it(), containsAllMethodsIn(allPublicStaticMethods(SeleniumExpressions.class)))
        .then(it(), containsAllMethodsIn(allPublicStaticMethods(CoreExpressions.class)));
    }

    public static Matcher<List<Method>> containsAllMethodsIn(List<Method> methods) {
        return new ContainsAllMethodsIn(methods);
    }

    private static class ContainsAllMethodsIn extends TypeSafeMatcher<List<Method>> {

        List<Method> methods;

        public ContainsAllMethodsIn(List<Method> methods) {
            this.methods = methods;
        }

        @Override
        protected void describeMismatchSafely(
                final List<Method> item, final Description mismatchDescription) {

            methods.stream().forEach(it ->{
                if (!contains(item, it)) {
                    mismatchDescription.appendText("\nIt does not contain: " + it);
                }
            });
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("Should contain all methods");
        }

        private static boolean equalMethods(Method m1, Method m2) {
            return equalNames(m1, m2) &&
                    equalTypes(m1.getParameterTypes(), m2.getParameterTypes());
        }

        private static boolean equalTypes(Class<?>[] type1, Class<?>[] type2) {
            if (type1.length != type2.length) {
                return false;
            }
            for (int i = 0; i < type1.length; i++) {
                if (!type1[i].getName().equals(type2[i].getName())) {
                    return false;
                }
            }
            return true;
        }

        private static boolean equalNames(Method m1, Method m2) {
            return m1.getName().equals(m2.getName());
        }

        private static boolean contains(List<Method> list, Method m) {
            return list.stream().anyMatch(it -> equalMethods(it, m));
        }

        @Override
        protected boolean matchesSafely(List<Method> comparedMethods) {
            return methods.stream().allMatch(it -> contains(comparedMethods, it));
        }
    }

    private static List<Method> allPublicStaticMethods(Class<?> expressionsClass) {
        Method[] methods = expressionsClass.getDeclaredMethods();
        Predicate<Method> isPublicStatic =
                (it -> Modifier.isPublic(it.getModifiers()) && Modifier.isStatic(it.getModifiers()));
        return Stream.of(methods).filter(isPublicStatic).collect(toList());
    }

}
