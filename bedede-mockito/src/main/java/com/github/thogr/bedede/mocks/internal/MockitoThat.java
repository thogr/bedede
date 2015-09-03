package com.github.thogr.bedede.mocks.internal;

import org.mockito.BDDMockito.BDDMyOngoingStubbing;

import com.github.thogr.bedede.mocks.That;

final class MockitoThat<T> extends That<BDDMyOngoingStubbing<T>> {

    MockitoThat(final BDDMyOngoingStubbing<T> stubbing) {
        super(stubbing);
    }
}
