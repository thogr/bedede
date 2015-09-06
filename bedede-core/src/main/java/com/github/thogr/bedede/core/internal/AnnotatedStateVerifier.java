package com.github.thogr.bedede.core.internal;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.github.thogr.bedede.annotations.OnEntry;
import com.github.thogr.bedede.conditions.Expecting;

class AnnotatedStateVerifier<T> implements StateVerifier<T> {

    private static final String VERIFY_FAILED = "Can't verify state ";
    private final Method onEntryMethod;
    private final ConditionController conditionController;

    AnnotatedStateVerifier(final Class<T> stateClass, final ConditionController controller) {
        this.conditionController = controller;
        onEntryMethod = findOnEntryMethod(stateClass);
    }

    private Method findOnEntryMethod(final Class<? super T> stateClass) {
        final Method[] declaredMethods = stateClass.getDeclaredMethods();
        for (final Method method : declaredMethods) {
            if (method.isAnnotationPresent(OnEntry.class)) {
                return method;
            }
        }
        final Class<? super T> superclass = stateClass.getSuperclass();
        if (superclass != null) {
            return findOnEntryMethod(superclass);
        }
        return null;
    }

    @Override
    public final void verify(final T state) {
        try {
            if (onEntryMethod != null) {
                onEntryMethod.setAccessible(true);
                verify((Expecting<?>) onEntryMethod.invoke(state));
            }
        } catch (final IllegalAccessException e) {
            verifyFailed(state, e);
        } catch (final IllegalArgumentException e) {
            verifyFailed(state, e);
        } catch (final InvocationTargetException e) {
            final Throwable cause = e.getCause();
            if (cause instanceof AssertionError) {
                throw (AssertionError) cause;
            }
            verifyFailed(state, e);
        }
    }

    private void verify(final Expecting<?> condition) {
        if (condition != null) {
            conditionController.verify(condition);
        }
    }

    private void verifyFailed(final T state, final Exception e) {
        final String name = state.getClass().getName();
        throw new IllegalArgumentException(VERIFY_FAILED + name, e);
    }
}
