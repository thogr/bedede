package com.github.thogr.bedede.mockito.internal;

import org.mockito.BDDMockito;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;
import org.mockito.BDDMockito.Then;

import com.github.thogr.bedede.MockitoExpressions;
import com.github.thogr.bedede.core.internal.Internal;
import com.github.thogr.bedede.mocks.Mocked;
import com.github.thogr.bedede.mocks.That;

public final class MockitoExpressionsImpl {

    @Internal
    public MockitoExpressionsImpl(final MockitoExpressions expressions) {
        if (expressions == null) {
            throw new NullPointerException();
        }
    }

    @Internal
    public <T> That<BDDMyOngoingStubbing<T>> that(final T methodCall) {
        return new MockitoThat<>(BDDMockito.given(methodCall));
    }

    @Internal
    public <T> Mocked<Then<T>> theMocked(final T mock) {
        return new MockitoMocked<>(BDDMockito.then(mock));
    }
}
