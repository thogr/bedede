package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.BiActionExpression;
import com.github.thogr.bedede.BiPerforming;

final class BiPerformingImpl<T1, T2> extends BiPerforming<T1, T2> {
    BiPerformingImpl(BiActionExpression<T1, T2> expr) {
        super(expr);
    }
}
