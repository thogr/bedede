package com.github.thogr.bedede.mocks;

import org.mockito.BDDMockito;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.mockito.BDDMockito.Then;

final class MockExpressionsImplementations {

    private MockExpressionsImplementations() {
    }

    static <T> That<BDDMyOngoingStubbing<T>> that(T methodCall) {
        return new That<>(BDDMockito.given(methodCall));
    }

    static <T> Mocked<Then<T>> theMocked(T mock) {
        return new Mocked<>(BDDMockito.then(mock));
    }
}
