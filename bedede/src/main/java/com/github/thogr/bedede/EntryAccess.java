package com.github.thogr.bedede;

public interface EntryAccess<T> {

    <S> TargetAssuming<T, S> given(final Class<S> state);

    <S> TargetAssuming<T, S> given(final Entry<S> entry);

    <S> TargetAssuming<T, S> assuming(final Class<S> state);

    void then(final Class<T> state);

}
