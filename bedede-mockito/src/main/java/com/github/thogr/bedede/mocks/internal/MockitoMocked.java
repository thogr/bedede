package com.github.thogr.bedede.mocks.internal;

import org.mockito.BDDMockito.Then;

import com.github.thogr.bedede.mocks.Mocked;


final class MockitoMocked<T> extends Mocked<Then<T>> {
    MockitoMocked(final Then<T> spec) {
        super(spec);
    }
}
