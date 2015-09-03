package com.github.thogr.bedede;



public interface SecondGiven<T1, T2>
extends SecondWith<T1, T2>,
        SecondWithBehaviorExpression<T1, T2> {
    SecondWith<T1, T2> with(BiActionExpression<? super T1, ? super T2> action);

}
