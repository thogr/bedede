package com.github.thogr.bedede.core.internal;

import static com.github.thogr.bedede.core.internal.Framework.createConditionController;

import com.github.thogr.bedede.ActionExpression;
import com.github.thogr.bedede.GivenElement;
import com.github.thogr.bedede.ThenExpectingElement;
import com.github.thogr.bedede.WhenElement;
import com.github.thogr.bedede.conditions.Expecting;
public final class GivenElementImpl<T> implements GivenElement<T> {

    private final T elem;

    GivenElementImpl(final T elem) {
        this.elem = elem;
    }

    /* (non-Javadoc)
	 * @see com.github.thogr.bedede.GivenElement#when(com.github.thogr.bedede.ActionExpression)
	 */
    @Override
	public WhenElement<T> when(final ActionExpression<T> expression) {
        expression.perform(elem);
        return new WhenElementImpl<T>(elem);
    }

    public static <E> GivenElement<E> given(
            final Expecting<?> expecting) {
        @SuppressWarnings("unchecked")
        final E elem = (E) createConditionController().verify(expecting);
        return new GivenElementImpl<E>(elem);
    }

    /* (non-Javadoc)
	 * @see com.github.thogr.bedede.GivenElement#then(com.github.thogr.bedede.conditions.Expecting)
	 */
    @Override
	public ThenExpectingElement<T> then(final Expecting<?> exp) {
        return new ThenExpectingElementImpl<>(exp);
    }
}
