package com.github.thogr.bedede.mocks;

import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.mockito.BDDMockito.Then;

import com.github.thogr.bedede.mocks.internal.MockExpressionsImpl;

public final class MockExpressions {

    private static MockExpressionsImpl impl =
            new MockExpressionsImpl(new MockExpressions());

    private MockExpressions() {
    }

    public static <T> That<BDDMyOngoingStubbing<T>> that(T methodCall) {
        return impl.that(methodCall);
    }

    public static <T> Mocked<Then<T>> theMocked(T mock) {
        return impl.theMocked(mock);
    }
}
