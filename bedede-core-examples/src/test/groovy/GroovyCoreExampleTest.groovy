import static com.github.thogr.bedede.GroovyCoreExpressions.*
import static org.hamcrest.CoreMatchers.is
import static org.junit.Assert.*

import org.junit.Test

class GroovyCoreExampleTest {

    @Test
    public void nestedWithNoInnerGiven() {
        given (a: new StringBuffer("Hello")) {
            when performing: {it.append(" World!")}
            then expect: {it.toString()}, is: "Hello World!"
        }
    }

    @Test
    public void nestedWithInnerGiven() {
        given (a: new StringBuffer("Hello")) { buf ->
            given a: " World!"
            when performing: {buf.append(it)}
            then expect: {buf.toString()}, is: "Hello World!"
        }
    }

    @Test
    public void nestedWithInnerNestedGivenWithNoInnerGiven() {
        given (a: new StringBuffer("Hello")) { buf ->
            given (a: " World!") {
                when performing: {buf.append(it)}
                then expect: {buf.toString()}, is: "Hello World!"
            }
        }
    }
}
