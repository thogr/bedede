package com.github.thogr.bedede;

import org.mockito.BDDMockito;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;

import com.github.thogr.bedede.mockito.internal.MockitoExpressionsImpl;
import com.github.thogr.bedede.mocks.Mocked;
import com.github.thogr.bedede.mocks.That;

public final class MockitoExpressions {

    private static MockitoExpressionsImpl impl =
            new MockitoExpressionsImpl(new MockitoExpressions());

    private MockitoExpressions() {
    }

    /**
     * Wraps a method call in a {@link That} object so that Mockito stubbing can be used
     * in a given()-expression, without name conflict with {@link BDDMockito#given(Object)}.
     * <p>
     * Example:
     * </p>
     * <pre>
     * given(that(mock.foo())).willReturn(123);
     * </pre>
     * This is equivalent to
     * <pre>
     * BddMockito.given(mock.foo()).willReturn(123)
     * </pre>
     * or
     * <pre>
     * Mockito.when(mock.foo()).thenReturn(123)
     * </pre>
     * @param <T> the return type of method called
     * @param methodCall call to a method to be stubbed
     * @return {@link BDDMyOngoingStubbing} wrapped in a {@link That}
     */
    public static <T> That<BDDMyOngoingStubbing<T>> that(final T methodCall) {
        return impl.that(methodCall);
    }

    public static <T> Mocked<BDDMockito.Then<T>> theMocked(final T mock) {
        return impl.theMocked(mock);
    }
}
