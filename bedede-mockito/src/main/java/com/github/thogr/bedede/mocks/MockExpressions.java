package com.github.thogr.bedede.mocks;

import org.mockito.BDDMockito;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;

import com.github.thogr.bedede.mocks.internal.MockExpressionsImpl;

public final class MockExpressions {

    private static MockExpressionsImpl impl =
            new MockExpressionsImpl(new MockExpressions());

    private MockExpressions() {
    }

    public static <T> That<BDDMyOngoingStubbing<T>> that(final T methodCall) {
        return impl.that(methodCall);
    }

    public static <T> Mocked<BDDMockito.Then<T>> theMocked(final T mock) {
        return impl.theMocked(mock);
    }
}
