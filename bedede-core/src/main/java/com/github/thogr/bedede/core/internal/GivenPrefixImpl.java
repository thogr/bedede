package com.github.thogr.bedede.core.internal;

import com.github.thogr.bedede.Given;
import com.github.thogr.bedede.GivenPrefix;
import com.github.thogr.bedede.core.CoreExpressions;
import com.github.thogr.bedede.state.Assuming;
import com.github.thogr.bedede.state.Entry;
import com.github.thogr.bedede.state.StateExpressions;


class GivenPrefixImpl implements GivenPrefix {

    GivenPrefixImpl() {

    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenPrefix#at(java.lang.Class)
     */
    @Override
    public <T> Assuming<T> at(final Class<T> state) {
        return StateExpressions.given(state);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenPrefix#at(com.github.thogr.bedede.Entry)
     */
    @Override
    public <T> Assuming<T> at(final Entry<T> entry) {
        return StateExpressions.given(entry);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenPrefix#a(T)
     */
    @Override
    public <T> Given<T> a(final T object) {
        return CoreExpressions.given(CoreExpressions.a(object));
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenPrefix#as(T)
     */
    @Override
    public <T> Given<T> an(final T object) {
        return a(object);
    }

    @Override
    public <T> Given<T> the(final T object) {
        return a(object);
    }
}
