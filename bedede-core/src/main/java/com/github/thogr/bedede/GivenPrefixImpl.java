package com.github.thogr.bedede;

public class GivenPrefixImpl implements GivenPrefix {

    protected GivenPrefixImpl(final Framework only) {
        Framework.check(only);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenPrefix#at(java.lang.Class)
     */
    @Override
    public <T> Assuming<T> at(final Class<T> state) {
        return Bedede.given(state);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenPrefix#at(com.github.thogr.bedede.Entry)
     */
    @Override
    public <T> Assuming<T> at(final Entry<T> entry) {
        return Bedede.given(entry);
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenPrefix#a(T)
     */
    @Override
    public <T> GivenBehaviorExpression<T> a(final T object) {
        return Bedede.given(Bedede.a(object));
    }

    /* (non-Javadoc)
     * @see com.github.thogr.bedede.GivenPrefix#as(T)
     */
    @Override
    public <T> GivenBehaviorExpression<T> an(final T object) {
        return a(object);
    }

}
