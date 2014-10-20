package com.github.thogr.bedede;

import static com.github.thogr.bedede.Defining.building;

import com.github.thogr.bedede.Defining.DefiningEntry;
import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;

public abstract class Bedede {

    protected Bedede(final Framework only) {
        if (only == null) {
            throw new NullPointerException();
        }
    }

    public static Expecting<BooleanCondition> expecting(
            final Boolean condition, final Otherwise otherwise) {
        return Expecting.expecting(() -> condition, BooleanCondition.class, otherwise);
    }

    public static <T> Expecting<T> expecting(
            final T condition, final Otherwise otherwise) {
        @SuppressWarnings("unchecked")
        final Class<T> conditionClass = (Class<T>) condition.getClass();
        return Expecting.expecting(condition, conditionClass, otherwise);
    }

    public static Otherwise otherwise(final String message) {
        return Otherwise.otherwise(message);
    }

    public static <T> DefiningEntry<T> entry(final Class<T> state) {
        return building().entry(state);
    }
}
