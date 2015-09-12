// CHECKSTYLE:OFF MagicNumber

package com.github.thogr.bedede.examples;

import static com.github.thogr.bedede.CoreExpressions.a;
import static com.github.thogr.bedede.CoreExpressions.given;
import static com.github.thogr.bedede.CoreExpressions.it;
import static com.github.thogr.bedede.CoreExpressions.retrieving;
import static com.github.thogr.bedede.MockitoExpressions.that;
import static com.github.thogr.bedede.MockitoExpressions.theMocked;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class MockingExampleTest {

    static class Counter {
        private Incrementable inc;

        public Counter(final Incrementable inc) {
            this.inc = inc;
        }

        public int count() {
            inc.increment();
            return inc.getValue();
        }
    }

    @Mock
    private Incrementable mock;

    @Test
    public void shouldMock() throws Exception {
        given(that(mock.getValue())).willReturn(123);
        given(a(new Counter(mock)))
        .when(retrieving(Counter::count))
        .then(it(), is(123))
        .then(theMocked(mock)).should().increment();
    }

}
