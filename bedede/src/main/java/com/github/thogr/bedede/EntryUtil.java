package com.github.thogr.bedede;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import com.github.thogr.bedede.annotations.DefaultEntry;

final class EntryUtil {

    private EntryUtil() {

    }

    static <T> Entry<T> getDefaultEntry(final Class<T> state) {
        Entry<T> defaultEntry = null;
        for (final Field f : state.getDeclaredFields()) {
            if (f.getAnnotation(DefaultEntry.class) != null) {
                if (isPublicStatic(f)) {
                    defaultEntry = theOnlyDefaultEntry(state, defaultEntry, f);
                }
            }
        }
        if (defaultEntry != null) {
            return defaultEntry;
        }
        throw new IllegalArgumentException(String.format("The class %s has no Entry", state.toString()));
    }

    @SuppressWarnings("unchecked")
    private static <T> Entry<T> theOnlyDefaultEntry(
            final Class<T> viewClass,
            final Entry<T> defaultEntry,
            final Field f) {
        try {
            if (defaultEntry != null) {
                throw new IllegalArgumentException(String.format("The class % has multiple @Default entries", viewClass.toString()));
            }
            return (Entry<T>) f.get(null);
        } catch (final IllegalAccessException e) {
            throw new IllegalStateException(e);
        } catch (final ClassCastException e) {
            throw new IllegalArgumentException(errorWrongType(viewClass), e);
        }
    }

    private static <T> String errorWrongType(final Class<T> viewClass) {
        return "Default Entry of type Entry<" +
                viewClass + ">";
    }

    private static boolean isPublicStatic(final Field f) {
        final int modifiers = f.getModifiers();
        final boolean isPublicStatic = Modifier.isPublic(modifiers) && Modifier.isStatic(modifiers);
        return isPublicStatic;
    }
}
