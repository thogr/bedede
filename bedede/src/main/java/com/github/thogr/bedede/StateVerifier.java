package com.github.thogr.bedede;

import com.github.thogr.bedede.annotations.State;

public interface StateVerifier<T> {
    void verify(T state);

    static class Default implements StateVerifier<Object> {

        @Override
        public void verify(final Object state) {
        }
    }

    @SuppressWarnings("unchecked")
    static <S> StateVerifier<? super S> verifierOf(final Class<? super S> state) {
        final State stateAnnotation = state.getAnnotation(State.class);
        if (stateAnnotation != null) {
            final Class<? extends StateVerifier<?>> verifier = stateAnnotation.verifier();
            if (Default.class.equals(verifier)) {
                return new AnnotatedStateVerifier<>(state);
            }
            try {
                return (StateVerifier<S>) verifier.newInstance();
            } catch (final InstantiationException e) {
                throw new IllegalStateException(e);
            } catch (final IllegalAccessException e) {
                throw new IllegalStateException(e);
            }
        } else {
            final Class<? super S> superClass = state.getSuperclass();
            if (superClass != null) {
               final StateVerifier<? super S> superVerifier = verifierOf(superClass);
               if (superVerifier != null) {
                   return superVerifier;
               } else {
                   return new AnnotatedStateVerifier<>(state);
               }
            } else {
                return null;
            }
        }
    }
}
