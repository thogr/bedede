package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.Assuming;
import com.github.thogr.bedede.Entry;
import com.github.thogr.bedede.GivenBehaviorExpression;
import com.github.thogr.bedede.GivenPrefix;
import com.github.thogr.bedede.core.CoreExpressions;


class GivenPrefixImpl implements GivenPrefix {

    GivenPrefixImpl() {

    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenPrefix#at(java.lang.Class)
     */
    @Override
    public <T> Assuming<T> at(final Class<T> state) {
        return CoreExpressions.given(state);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenPrefix#at(com.github.thogr.bedede.Entry)
     */
    @Override
    public <T> Assuming<T> at(final Entry<T> entry) {
        return CoreExpressions.given(entry);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenPrefix#a(T)
     */
    @Override
    public <T> GivenBehaviorExpression<T> a(final T object) {
        return CoreExpressions.given(CoreExpressions.a(object));
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenPrefix#as(T)
     */
    @Override
    public <T> GivenBehaviorExpression<T> an(final T object) {
        return a(object);
    }

    @Override
    public <T> GivenBehaviorExpression<T> the(T object) {
        return a(object);
    }
}
