package com.github.thogr.bedede.core.internal;
import static org.junit.Assert.assertThat;

import java.util.function.BiFunction;

import org.hamcrest.Matcher;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.BiActionExpression;
import com.github.thogr.bedede.BiPerforming;
import com.github.thogr.bedede.BiTransforming;
import com.github.thogr.bedede.ContinuedWhen;
import com.github.thogr.bedede.SecondGiven;
import com.github.thogr.bedede.SecondWith;
import com.github.thogr.bedede.ThenItMatches;
import com.github.thogr.bedede.When;

class SecondBehaviorExpressionImpl<T1, T2>
    extends BehaviorExpressionImpl<T1>
    implements SecondGiven<T1, T2>, ContinuedWhen<T1, T2>, ThenItMatches<T1, T2> {

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
    public final <S> ThenItMatches<T1, T2> then(
            final BiFunction<? super T1, ? super T2, S> it, final Matcher<? super S> is) {
        S result = it.apply(first, second);
        assertThat(result, is);
        return new SecondBehaviorExpressionImpl<T1, T2>(first, second);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.SecondBehaviorExpression#when(com.github.thogr.bedede.BiActionExpression)
     */
    @Override
    public final SecondWith<T1, T2> with(
            final BiActionExpression<? super T1, ? super T2> action) {
        return perform(action);
    }

    private SecondBehaviorExpressionImpl<T1, T2> perform(
            final BiActionExpression<? super T1, ? super T2> action) {
        action.perform(first, second);
        return new SecondBehaviorExpressionImpl<T1, T2>(first, second);
    }

    @Override
    public SecondWith<T1, T2> with(
            ActionExpression<? super T2> action) {
        action.perform(second);
        return new SecondBehaviorExpressionImpl<T1, T2>(first, second);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.SecondBehaviorExpression#when(com.github.thogr.bedede.BiPerformingExpression)
     */
    @Override
    public final ContinuedWhen<T1, T2> when(
            final BiPerforming<? super T1, ? super T2> expr) {
        return whenPerforming(expr);
    }

    private ContinuedWhen<T1, T2> whenPerforming(
            final AbstractBiPerformer<? super T1, ? super T2> expr) {
        return perform(expr.getAction());
    }

    @Override
    public <S> When<S> when(
            BiTransforming<? super T1, ? super T2, ? extends S> expr) {
        return whenTransforming(expr);
    }

    private <S> When<S> whenTransforming(
            AbstractBiTransformer<? super T1, ? super T2, ? extends S> expr) {
        S result = expr.getFunction().apply(first, second);
        return new BasicBehaviorExpressionImpl<S>(result);
    }

}
