package com.github.thogr.bedede;


public interface ContinuedBehaviorExpression<T1, T2>
    extends BehaviorExpression<T1>, SecondBehavior<T1, T2> {

    ContinuedBehaviorExpression<T1, T2> when(
            BiPerforming<? super T1, ? super T2> expr);

    <S> BehaviorExpression<S> when(
            BiTransforming<? super T1, ? super T2, ? extends S> expr);

}
