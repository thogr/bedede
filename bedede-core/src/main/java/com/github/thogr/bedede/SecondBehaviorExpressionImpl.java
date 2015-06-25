package com.github.thogr.bedede;
import static org.junit.Assert.assertThat;

import java.util.function.BiFunction;

import org.hamcrest.Matcher;

class SecondBehaviorExpressionImpl<T1, T2>
    extends GivenBehaviorExpressionImpl<T1> implements SecondBehaviorExpression<T1, T2> {

    private T1 first;
    private T2 second;

    SecondBehaviorExpressionImpl(T1 first, T2 second) {
        super(first);
        this.first = first;
        this.second = second;
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.SecondBehaviorExpression#then(java.util.function.BiFunction, org.hamcrest.Matcher)
     */
    @Override
    public final <S> SecondBehaviorExpression<T1, T2> then(
            final BiFunction<? super T1, ? super T2, S> it, final Matcher<? super S> is) {
        S result = it.apply(first, second);
        assertThat(result, is);
        return new SecondBehaviorExpressionImpl<T1, T2>(first, second);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.SecondBehaviorExpression#when(com.github.thogr.bedede.BiActionExpression)
     */
    @Override
    public final SecondBehaviorExpression<T1, T2> when(
            final BiActionExpression<? super T1, ? super T2> action) {
        action.perform(first, second);
        return new SecondBehaviorExpressionImpl<T1, T2>(first, second);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.SecondBehaviorExpression#when(com.github.thogr.bedede.BiPerformingExpression)
     */
    @Override
    public final SecondBehaviorExpression<T1, T2> when(
            final BiPerformingExpression<? super T1, ? super T2> expr) {
        return when(expr.action);
    }
}
