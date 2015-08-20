package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.Bedede.given;
import static com.github.thogr.bedede.Bedede.it;
import static com.github.thogr.bedede.Bedede.retrieving;
import static com.github.thogr.bedede.mockito.Expressions.that;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.BDDMockito.then;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.github.thogr.bedede.examples.BehaviorExpressionExampleTest.Incrementable;

@RunWith(MockitoJUnitRunner.class)
public class MockingExampleTest {

    static class Counter {
        Incrementable inc;

        public Counter(Incrementable inc) {
            this.inc = inc;
        }

        public int count() {
            inc.increment();
            return inc.getValue();
        }
    }

    @Mock
    Incrementable mock;

    @Test
    public void shouldMock() throws Exception {
        given(that(mock.getValue())).willReturn(123);
        given(new Counter(mock))
        .when(retrieving(Counter::count))
        .then(it(), is(123));
        then(mock).should().increment();
    }

}
