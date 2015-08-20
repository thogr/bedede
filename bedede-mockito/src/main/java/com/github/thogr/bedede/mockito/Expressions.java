package com.github.thogr.bedede.mockito;

import org.mockito.BDDMockito;
import org.mockito.BDDMockito.BDDMyOngoingStubbing;

import com.github.thogr.bedede.mocks.That;

public class Expressions {
     public static <T> That<BDDMyOngoingStubbing<T>> that(T methodCall) {
         return new That<>(BDDMockito.given(methodCall));
     }
}
