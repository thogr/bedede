package com.github.thogr.bedede.mocks.internal;

import org.mockito.BDDMockito;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.mockito.BDDMockito.Then;

import com.github.thogr.bedede.mocks.MockExpressions;
import com.github.thogr.bedede.mocks.Mocked;
import com.github.thogr.bedede.mocks.That;

public final class MockExpressionsImpl {

    public MockExpressionsImpl(MockExpressions expressions) {
        if (expressions == null) {
            throw new NullPointerException();
        }
    }

    public <T> That<BDDMyOngoingStubbing<T>> that(T methodCall) {
        return new MockitoThat<>(BDDMockito.given(methodCall));
    }

    public <T> Mocked<Then<T>> theMocked(T mock) {
        return new MockitoMocked<>(BDDMockito.then(mock));
    }
}
