package com.github.thogr.bedede;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.function.Function;

abstract class Expressions {
    static <T> BehaviorExpression<T> given(final T obj) {
        return new BasicBehaviorExpression<T>(obj);
    }

    static <T> BehaviorExpression<T> given(final BehaviorExpression<T> expr) {
        return expr;
    }

    static <T> PerformingExpression<T> performing(final ActionExpression<T> expr) {
        return new PerformingExpression<T>(expr);
    }

    static <T> Function<T, T> it() {
        return (it -> it);
    }

    static <T> Function<T, Object> the(String functionName) {
        return new Function<T, Object>() {
            @Override
            public Object apply(T obj) {
                Class<?> clazz = obj.getClass();
                try {
                    Method method = clazz.getMethod(functionName);
                    return method.invoke(obj);
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
