package com.github.thogr.bedede.core;

import com.github.thogr.bedede.core.internal.Internal;
import com.github.thogr.bedede.core.internal.Wrapped;

public final class AnObject<T> extends Wrapped<T> {

    AnObject(final T object) {
        super(object);
    }

    @Internal
    public static <T> AnObject<T> a(final T object) {
        return new AnObject<T>(object);
    }

}