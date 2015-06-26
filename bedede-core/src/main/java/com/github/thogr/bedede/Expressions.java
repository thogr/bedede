package com.github.thogr.bedede;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;

abstract class Expressions {

    static <T> GivenBehaviorExpression<T> given(final T obj) {
        return new GivenBehaviorExpressionImpl<T>(obj);
    }

    static <T> GivenBehaviorExpression<T> given(final BehaviorExpression<T> expr) {
        return new GivenBehaviorExpressionImpl<T>(expr);
    }

    static <T> PerformingExpression<T> performing(final ActionExpression<T> expr) {
        return new PerformingExpression<T>(expr);
    }

    static <T1, T2> BiPerformingExpression<T1, T2>
        performing(final BiActionExpression<T1, T2> expr) {
        return new BiPerformingExpression<T1, T2>(expr);
    }

    static <T> Function<T, T> it() {
        return (it -> it);
    }

    static <T, S> Function<T, S> the(Function<T, S> it) {
        return it;
    }

    static <T> Predicate<T> the(Predicate<T> it) {
        return it;
    }

    static <T1, T2, S> BiFunction<T1, T2, S> the(BiFunction<T1, T2, S> it) {
        return it;
    }

    static <T, S> Function<T, S> the(String functionName) {
        return new Function<T, S>() {
            @SuppressWarnings("unchecked")
            @Override
            public S apply(T obj) {
                Class<?> clazz = obj.getClass();
                try {
                    Method method = clazz.getMethod(functionName);
                    return (S) method.invoke(obj);
                } catch (NoSuchMethodException e) {
                    throw new IllegalArgumentException(e);
                } catch (SecurityException e) {
                    throw new IllegalArgumentException(e);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException(e);
                } catch (InvocationTargetException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        };
    }

    static <T> ActionExpression<T> theAction(String actionName) {
        return new ActionExpression<T>() {
            @Override
            public void perform(T obj) {
                Class<?> clazz = obj.getClass();
                try {
                    Method method = clazz.getMethod(actionName);
                    method.invoke(obj);
                } catch (NoSuchMethodException e) {
                    throw new IllegalArgumentException(e);
                } catch (SecurityException e) {
                    throw new IllegalArgumentException(e);
                } catch (IllegalAccessException e) {
                    throw new IllegalArgumentException(e);
                } catch (InvocationTargetException e) {
                    throw new IllegalArgumentException(e);
                }
            }
        };
    }

    static <T,S> TransformingExpression<T, S> retrieving(Function<T, S> expr) {
        return transforming(expr);
    }

    static <T,S> TransformingExpression<T, S> transforming(Function<T, S> expr) {
        return new TransformingExpression<T, S>(expr);
    }
}
