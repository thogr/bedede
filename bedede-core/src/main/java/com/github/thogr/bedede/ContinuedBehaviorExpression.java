package com.github.thogr.bedede;


public interface ContinuedBehaviorExpression<T1, T2>
    extends BehaviorExpression<T1>, ContinuedBehavior<T1, T2> {

    ContinuedWhen<T1, T2> when(
            BiPerforming<? super T1, ? super T2> expr);

    <S> WhenBiTransforming<S> when(
            BiTransforming<? super T1, ? super T2, ? extends S> expr);

}
