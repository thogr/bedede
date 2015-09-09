package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.core.BiActionExpression;
import com.github.thogr.bedede.core.BiPerforming;

final class BiPerformingImpl<T1, T2> extends BiPerforming<T1, T2> {

    BiPerformingImpl(final BiActionExpression<T1, T2> expr) {
        super(expr);
    }
}
