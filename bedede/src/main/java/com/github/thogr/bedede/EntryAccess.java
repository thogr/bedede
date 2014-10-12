package com.github.thogr.bedede;

public interface EntryAccess<S> {

    <T> Assuming<T> given(final Class<T> state);

    <T> Assuming<T> given(final Entry<T> entry);

    <T> Assuming<T> assuming(final Class<T> state);

    void then(final Class<S> state);

}
