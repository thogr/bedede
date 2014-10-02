package com.github.thogr.bedede;

final class Expecting {

    private Expecting() {

    }

    @SuppressWarnings("unchecked")
    static <T> Condition<T> expecting(
            final T condition, final Otherwise otherwise) {
        return new Condition<T>((Class<T>) condition.getClass()) {

            @Override
            void verify(
                    final ConditionVerifier<T> verifier) {
                verifier.verify(condition, otherwise);
            }
        };
    }

}
