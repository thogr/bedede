package com.github.thogr.bedede.internal;

import org.mockito.BDDMockito.BDDMyOngoingStubbing;

import com.github.thogr.bedede.CoreExpressions;
import com.github.thogr.bedede.GivenPrefix;
import com.github.thogr.bedede.MockitoExpressions;
import com.github.thogr.bedede.StateExpressions;
import com.github.thogr.bedede.core.Given;
import com.github.thogr.bedede.state.Assuming;
import com.github.thogr.bedede.state.Entry;


class GivenPrefixImpl implements GivenPrefix {

    GivenPrefixImpl() {

    }

    @Override
    public <T> Assuming<T> at(final Class<T> state) {
        return StateExpressions.given(state);
    }

    @Override
    public <T> Assuming<T> at(final Entry<T> entry) {
        return StateExpressions.given(entry);
    }

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

    @Override
    public <T> BDDMyOngoingStubbing<T> that(final T call) {
        return CoreExpressions.given(MockitoExpressions.that(call));
    }
}
