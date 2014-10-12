package com.github.thogr.bedede;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.github.thogr.bedede.annotations.DefaultEntry;
import com.github.thogr.bedede.annotations.InitialState;

final class EntryFinder {

    private static final String NO_DEFAULT_ENTRY = "The class %s has no default Entry";

    private EntryFinder() {

    }

    static <T> Entry<T> getDefaultEntry(final Class<T> state) {
        Entry<T> defaultEntry = null;
        Field defaultEntryField = null;
        for (final Field f : state.getDeclaredFields()) {
            if (isPublicStatic(f) && f.getType().equals(Entry.class)) {
                if (f.getAnnotation(DefaultEntry.class) != null) {
                    defaultEntry = theOnlyDefaultEntry(state, defaultEntry, f);
                    defaultEntryField = f;
                } else {
                    defaultEntry = theOnlyEntry(state, defaultEntry, defaultEntryField, f);
                    defaultEntryField = f;
                }
            }
        }
        if (defaultEntry != null) {
            return defaultEntry;
        } else if (defaultEntryField == null) {
            if (state.isAnnotationPresent(InitialState.class)){
                return null;
            }
        }
        throw new IllegalArgumentException(String.format(NO_DEFAULT_ENTRY, state.toString()));
    }

    @SuppressWarnings("unchecked")
    private static <T> Entry<T> theOnlyEntry(
            final Class<T> stateClass,
            final Entry<T> defaultEntry,
            final Field defaultEntyField,
            final Field f) {
        try {
            if (defaultEntyField != null) {
                if (defaultEntyField.isAnnotationPresent(DefaultEntry.class)) {
                    return defaultEntry;
                } else {
                    return null;
                }
            }
            if (Entry.class.isAssignableFrom(f.getType())) {
                final Object value = f.get(null);
                final Entry<T> entry = (Entry<T>) value;
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
        }
    }

    @SuppressWarnings("unchecked")
    private static <T> Entry<T> theOnlyDefaultEntry(
            final Class<T> stateClass,
            final Entry<T> defaultEntry,
            final Field f) {
        try {
            if (defaultEntry != null) {
                throw new IllegalArgumentException(String.format("The class % has multiple default entries", stateClass.toString()));
            }
            return (Entry<T>) f.get(null);
        } catch (final IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (final ClassCastException e) {
            throw new IllegalArgumentException(errorWrongType(stateClass), e);
        }
    }

    private static String errorWrongType(final Class<?> type) {
        return "Default Entry of must be type: Entry<" + type.getName() + ">";
    }

    private static boolean isPublicStatic(final Field f) {
        final int modifiers = f.getModifiers();
        final boolean isPublicStatic = Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers);
        return isPublicStatic;
    }
}
