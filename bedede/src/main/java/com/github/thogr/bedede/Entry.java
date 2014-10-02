package com.github.thogr.bedede;
/**
 * The behavior needed reach a a target state.
 * The method {@link #perform()} is the implementation.
 * Typically the first step is to refer to some other Entry,
 * by calling {@link #given(Entry)},
 * and then some actions, and finally a call to {@link #then(Class)}
 *
 * @param <S> the target state
 */
public abstract class Entry<S> extends Behavior<S> {

    public Entry() {

    }

    protected final <T> GivenResult<T> given(final Class<T> state) {
        return getController().given(state);
    }

    protected final <T> GivenResult<T> given(final Entry<T> entry) {
        return getController().given(entry);
    }

    protected final <T> void when(final Action<? super T> action) {
        getController().when(action);
    }

    protected final <T> void then(final Class<T> state) {
        getController().then(state);
    }

}
