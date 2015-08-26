package com.github.thogr.bedede;

import com.github.thogr.bedede.core.internal.Wrapped;

public final class AnObject<T> extends Wrapped<T> {

    AnObject(T object) {
        super(object);
    }

    public static <T> AnObject<T> a(T object) {
        return new AnObject<T>(object);
    }

}
