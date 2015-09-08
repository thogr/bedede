package com.github.thogr.bedede.state.internal;

import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import com.github.thogr.bedede.annotations.DefaultEntry;
import com.github.thogr.bedede.annotations.InitialState;
import com.github.thogr.bedede.state.Entry;

final class EntryFinder {

    private static final String NO_DEFAULT_ENTRY = "The class %s has no default Entry, nor is it" +
            " annotated @" +
            InitialState.class.getSimpleName();

    private EntryFinder() {

    }

    // CHECKSTYLE:OFF MethodLength
    static <T> AbstractInternalEntry<T> getDefaultEntry(final Class<T> state) {
        Member defaultEntryField = null;
        final List<Member> members = getMembers(state);
        AbstractInternalEntry<T> defaultEntry = null;
        for (final Member f : members) {
            if (isPublicStatic(f) && isEntry(f)) {
                if (isAnnotatedDefault(f)) {
                    defaultEntry = theOnlyDefaultEntry(state, defaultEntry, defaultEntryField, f);
                    defaultEntryField = f;
                } else {
                    final AbstractInternalEntry<T> entry =
                            theOnlyEntry(state, defaultEntry, defaultEntryField, f);
                    if (entry != defaultEntry) {
                        defaultEntry = entry;
                        defaultEntryField = f;
                    }
                }
            }
        }
        if (defaultEntry != null) {
            return defaultEntry;
        } else if (defaultEntryField == null) {
            if (state.isAnnotationPresent(InitialState.class)) {
                return null;
            }
        }
        throw new IllegalArgumentException(String.format(NO_DEFAULT_ENTRY, state.toString()));
    }
    //CHECKSTYLE:ON

    private static boolean isEntry(final Member f) {
        if (f instanceof Field) {
            return Entry.class.isAssignableFrom(((Field) f).getType());
        } else if (f instanceof Method) {
            final Method method = (Method) f;
            if (method.getParameterTypes().length > 0) {
                return false;
            }
            return  Entry.class.isAssignableFrom(method.getReturnType());
        }
        return false;
    }

    private static <T> List<Member> getMembers(final Class<T> state) {
        final List<Member> members = new ArrayList<>();
        for (final Method m : state.getDeclaredMethods()) {
            members.add(m);
        }
        for (final Field f : state.getDeclaredFields()) {
            members.add(f);
        }
        return members;
    }

    private static <T> AbstractInternalEntry<T> theOnlyEntry(
            final Class<T> stateClass,
            final AbstractInternalEntry<T> defaultEntry,
            final Member defaultEntyField,
            final Member f) {
        try {
            if (defaultEntyField != null) {
                if (isAnnotatedDefault(defaultEntyField)) {
                    return defaultEntry;
                } else {
                    return null;
                }
            }
            if (isEntry(f)) {
                final AbstractInternalEntry<T> entry = getValue(f);
                final Class<T> target = entry.getTarget();
                if (target.equals(stateClass)) {
                    return entry;
                } else {
                    throw new ClassCastException("Unexpected type Entry<" + target.getName() + ">");
                }
            }
            return null;
        } catch (final IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (final ClassCastException e) {
            throw new IllegalArgumentException(errorWrongType(stateClass), e);
        } catch (final InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Entry<T> getValue(final Member f)
            throws IllegalAccessException, InvocationTargetException {
        if (f instanceof Field) {
            return (Entry<T>) ((Field) f).get(null);
        } else {
            final Method method = (Method) f;
            if (method.getParameterTypes().length == 0) {
                return (Entry<T>) method.invoke(null);
            } else {
                throw new IllegalArgumentException(
                        "Default Entry method must not have any parameters");
            }
        }
    }

    private static <T> AbstractInternalEntry<T> theOnlyDefaultEntry(
            final Class<T> stateClass,
            final AbstractInternalEntry<T> defaultEntry,
            final Member defaultEntryField,
            final Member f) {
        try {
            if (defaultEntry != null && isAnnotatedDefault(defaultEntryField)) {
                throw new IllegalArgumentException(
                        String.format("The class %s has multiple default entries",
                                stateClass.toString()));
            }
            return getValue(f);
        } catch (final IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (final ClassCastException e) {
            throw new IllegalArgumentException(errorWrongType(stateClass), e);
        } catch (final InvocationTargetException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static boolean isAnnotatedDefault(final Member member) {
        return ((AnnotatedElement) member).isAnnotationPresent(DefaultEntry.class);
    }

    private static String errorWrongType(final Class<?> type) {
        return "Default Entry of must be type: Entry<" + type.getName() + ">";
    }

    private static boolean isPublicStatic(final Member f) {
        final int modifiers = f.getModifiers();
        final boolean isPublicStatic = Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers);
        return isPublicStatic;
    }
}
