package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.Bedede.a;
import static com.github.thogr.bedede.Bedede.given;
import static com.github.thogr.bedede.Bedede.it;
import static com.github.thogr.bedede.Bedede.retrieving;
import static com.github.thogr.bedede.mocks.MockExpressions.that;
import static com.github.thogr.bedede.mocks.MockExpressions.theMocked;
import static org.hamcrest.CoreMatchers.is;

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
        given(a(new Counter(mock)))
        .when(retrieving(Counter::count))
        .then(it(), is(123))
        .then(theMocked(mock)).should().increment();
    }

}