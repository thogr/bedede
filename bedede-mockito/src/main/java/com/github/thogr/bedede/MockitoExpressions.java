package com.github.thogr.bedede;

import org.mockito.BDDMockito;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;

import com.github.thogr.bedede.mockito.internal.MockExpressionsImpl;
import com.github.thogr.bedede.mocks.Mocked;
import com.github.thogr.bedede.mocks.That;

public final class MockitoExpressions {

    private static MockExpressionsImpl impl =
            new MockExpressionsImpl(new MockitoExpressions());

    private MockitoExpressions() {
    }

    public static <T> That<BDDMyOngoingStubbing<T>> that(final T methodCall) {
        return impl.that(methodCall);
    }

    public static <T> Mocked<BDDMockito.Then<T>> theMocked(final T mock) {
        return impl.theMocked(mock);
    }
}
