package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.conditions.BooleanCondition;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.conditions.Otherwise;
import com.github.thogr.bedede.core.internal.Internal;
import com.github.thogr.bedede.state.Assuming;
import com.github.thogr.bedede.state.Entry;
import com.github.thogr.bedede.state.GivenElement;
import com.github.thogr.bedede.state.StateExpressions;
import com.github.thogr.bedede.state.internal.Defining.DefiningEntry;

public class StateExpressionsImpl {

    @Internal
    public StateExpressionsImpl(final StateExpressions expr) {
        if (expr == null) {
            throw new NullPointerException();
        }
    }

    @Internal
    public <T> Assuming<T> given(final Class<T> state) {
        final BehaviorDriver driver = new BehaviorDriver();
        return driver.given(state);
    }

    @Internal
    public <T> Assuming<T> given(final Entry<T> entry) {
        final BehaviorDriver driver = new BehaviorDriver();
        return driver.given(entry);
    }

    @Internal
    public <T> DefiningEntry<T> entry(final Class<T> state) {
        return Defining.building().entry(state);
    }

    @Internal
    public Expecting<BooleanCondition> expecting(
            final Boolean condition, final Otherwise otherwise) {
        return ExpectingImpl.expecting(() -> condition, BooleanCondition.class, otherwise);
    }

    @Internal
    public static <E> GivenElement<E> given(final Expecting<?> precondition) {
        return GivenElementImpl.<E>given(precondition);
    }

    @Internal
    public static <T> Expecting<T> expecting(
            final T condition, final Otherwise otherwise) {
        @SuppressWarnings("unchecked")
        final Class<T> conditionClass = (Class<T>) condition.getClass();
        return ExpectingImpl.expecting(condition, conditionClass, otherwise);
    }
}
