package com.github.thogr.bedede.core.internal;

import static org.junit.Assert.assertThat;

import java.util.function.BiFunction;

import org.hamcrest.Matcher;

import com.github.thogr.bedede.core.ActionExpression;
import com.github.thogr.bedede.core.BiActionExpression;
import com.github.thogr.bedede.core.BiPerforming;
import com.github.thogr.bedede.core.BiTransforming;
import com.github.thogr.bedede.core.ContinuedBehaviorExpression;
import com.github.thogr.bedede.core.SecondGiven;
import com.github.thogr.bedede.core.SecondWith;
import com.github.thogr.bedede.core.ThenItMatches;
import com.github.thogr.bedede.core.WhenBiPerforming;
import com.github.thogr.bedede.core.WhenBiTransforming;

class SecondBehaviorExpressionImpl<F, T1, T2>
    extends BehaviorExpressionImpl<F>
    implements SecondGiven<F, T1, T2>, WhenBiPerforming<F, T1, T2>, ThenItMatches<F, T1, T2> {

    private final T1 first;
    private final T2 second;

    SecondBehaviorExpressionImpl(final F focus, final T1 first, final T2 second) {
        super(focus);
        this.first = first;
        this.second = second;
    }

    @Override
    public final <S> ThenItMatches<F, T1, T2> then(
            final BiFunction<? super T1, ? super T2, S> they, final Matcher<? super S> match) {
        return thenTheyMatch(they, match);
    }

    <S> ThenItMatches<F, T1, T2> thenTheyMatch(
            final BiFunction<? super T1, ? super T2, S> it,
            final Matcher<? super S> is) {

        final S result = it.apply(first, second);
        assertThat(result, is);
        return new SecondBehaviorExpressionImpl<>(getFocusedObject(), first, second);
    }

    @Override
    public final SecondWith<F, T1, T2> with(
            final BiActionExpression<? super T1, ? super T2> action) {
        return performBiAction(action);
    }

    SecondBehaviorExpressionImpl<F, T1, T2> performBiAction(
            final BiActionExpression<? super T1, ? super T2> action) {
        action.perform(first, second);
        return new SecondBehaviorExpressionImpl<>(getFocusedObject(), first, second);
    }

    @Override
    public SecondWith<F, T1, T2> with(
            final ActionExpression<? super T2> action) {
        return performAction(action);
    }

    SecondWith<F, T1, T2> performAction(
            final ActionExpression<? super T2> action) {
        action.perform(second);
        return new SecondBehaviorExpressionImpl<>(getFocusedObject(), first, second);
    }

    @Override
    public final WhenBiPerforming<F, T1, T2> when(
            final BiPerforming<? super T1, ? super T2> expr) {
        return whenPerforming(expr);
    }

    private WhenBiPerforming<F, T1, T2> whenPerforming(
            final AbstractBiPerformer<? super T1, ? super T2> expr) {
        return performBiAction(expr.getAction());
    }

    @Override
    public <S> WhenBiTransforming<S> when(
            final BiTransforming<? super T1, ? super T2, ? extends S> expr) {
        return whenTransforming(expr);
    }

    private <S> WhenBiTransforming<S> whenTransforming(
            final AbstractBiTransformer<? super T1, ? super T2, ? extends S> expr) {
        final S result = expr.getFunction().apply(first, second);
        return new BasicBehaviorExpressionImpl<>(result);
    }

    @Override
    public <S> ContinuedBehaviorExpression<S, T1, T2> given(
            final BiFunction<? super T1, ? super T2, ? extends S> expr) {
        final S result = expr.apply(first, second);
        return new SecondBehaviorExpressionImpl<>(result, first, second);
    }

    @Override
    public <S> ContinuedBehaviorExpression<S, T1, T2> and(
            final BiFunction<? super T1, ? super T2, ? extends S> expr) {
        return given(expr);
    }
}
