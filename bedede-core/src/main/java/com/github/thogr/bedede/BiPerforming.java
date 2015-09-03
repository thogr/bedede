package com.github.thogr.bedede;

import com.github.thogr.bedede.core.internal.AbstractBiPerformer;

public class BiPerforming<T1, T2> extends AbstractBiPerformer<T1, T2> {

    protected BiPerforming(final BiActionExpression<T1, T2> expr) {
        super(expr);
    }

}
