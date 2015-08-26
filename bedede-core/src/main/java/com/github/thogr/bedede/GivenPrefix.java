package com.github.thogr.bedede;



public interface GivenPrefix {

    <T> Assuming<T> at(Class<T> state);

    <T> Assuming<T> at(Entry<T> entry);

    <T> Given<T> a(T object);

    <T> Given<T> an(T object);

    <T> Given<T> the(T object);

}
