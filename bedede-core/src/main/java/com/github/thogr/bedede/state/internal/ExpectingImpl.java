package com.github.thogr.bedede.state.internal;

import com.github.thogr.bedede.Otherwise;
import com.github.thogr.bedede.conditions.ConditionVerifier;
import com.github.thogr.bedede.conditions.Expecting;
import com.github.thogr.bedede.core.internal.Internal;

public abstract class ExpectingImpl<T> extends Expecting<T> {

    private final Class<T> conditionClass;

    private ExpectingImpl(final Class<T> conditionClass) {
        this.conditionClass = conditionClass;
    }

    @Override
    public Expecting<T> and(final Expecting<T> other) {
        return new ExpectingImpl<T>(conditionClass) {

            @Override
            Object verify(final ConditionVerifier<T> verifier) {
                return verifyBoth(ExpectingImpl.this, other, verifier);
            }
        };
    }

    private static <T> Object verifyBoth(final Verifiable<T> self,
            final Verifiable<T> other, final ConditionVerifier<T> verifier) {
        self.verify(verifier);
        return other.verify(verifier);
    }

    @Override
    Class<T> getConditionClass() {
        return conditionClass;
    }

    @Internal
    public static <T> Expecting<T> expecting(
            final T condition, final Class<T> conditionClass, final Otherwise otherwise) {

        return new ExpectingImpl<T>(conditionClass) {

            @Override
            Object verify(final ConditionVerifier<T> verifier) {
                return verifier.verify(condition, otherwise);
            }
        };
    }
}
