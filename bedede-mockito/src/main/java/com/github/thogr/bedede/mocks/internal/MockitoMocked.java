package com.github.thogr.bedede.mocks.internal;

import org.mockito.BDDMockito.Then;

import com.github.thogr.bedede.mocks.Mocked;


class MockitoMocked<T> extends Mocked<Then<T>> {
    MockitoMocked(Then<T> spec) {
        super(spec);
    }
}
