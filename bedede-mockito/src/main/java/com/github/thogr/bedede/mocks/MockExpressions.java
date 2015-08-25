package com.github.thogr.bedede.mocks;

import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.mockito.BDDMockito.Then;

public final class MockExpressions {

    private MockExpressions() {
    }

    public static <T> That<BDDMyOngoingStubbing<T>> that(T methodCall) {
        return MockExpressionsImplementations.that(methodCall);
    }

    public static <T> Mocked<Then<T>> theMocked(T mock) {
        return MockExpressionsImplementations.theMocked(mock);
    }
}
