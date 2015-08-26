package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.ThenElement;
import com.github.thogr.bedede.ThenExpectingElement;
import com.github.thogr.bedede.conditions.Expecting;

public final class ThenExpectingElementImpl<E> implements ThenExpectingElement<E> {

    ThenExpectingElementImpl(final Expecting<?> exp) {
        GivenElementImpl.<E>given(exp);
    }

    /* (non-Javadoc)
	 * @see com.github.thogr.bedede.ThenExpectingElement#then(com.github.thogr.bedede.conditions.Expecting)
	 */
    @Override
	public ThenExpectingElement<E> then(final Expecting<?> exp) {
        return new ThenExpectingElementImpl<>(exp);
    }

    /* (non-Javadoc)
	 * @see com.github.thogr.bedede.ThenExpectingElement#then()
	 */
    @Override
	public ThenElement<E> then() {
        return new ThenElementImpl<E>();
    }
}
