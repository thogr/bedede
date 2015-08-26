package com.github.thogr.bedede;



public interface GivenPrefix {

    <T> Assuming<T> at(Class<T> state);

    <T> Assuming<T> at(Entry<T> entry);

    <T> GivenBehaviorExpression<T> a(T object);

    <T> GivenBehaviorExpression<T> an(T object);

    <T> GivenBehaviorExpression<T> the(T object);

}
