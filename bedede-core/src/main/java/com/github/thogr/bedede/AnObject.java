package com.github.thogr.bedede;

import com.github.thogr.bedede.core.internal.Wrapped;

public final class AnObject<T> extends Wrapped<T> {

    AnObject(final T object) {
        super(object);
    }

    public static <T> AnObject<T> a(final T object) {
        return new AnObject<T>(object);
    }

}
