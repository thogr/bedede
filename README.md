# Bedede
======
Uncle Bob said:
> *The Given/When/Then syntax of BDD seemed eerily familiar when I first heard about it several years ago. It’s been tickling at the back of my brain since then. Something about that triplet was trying to resonate with something else in my brain.*
> *This strange similarity caused me to realize that GIVEN/WHEN/THEN is simply a state transition, and that BDD is really just a way to describe a finite state machine.*

## A simple state based BDD framework for Java

**Note:**
Currently I'm experimenting a lot. So don't expect a stable API, just yet.

But, to give you an idea what Bedede is:

* It's a BDD-test framework.
* It supports State-based tests. (That's very much like Model-based testing, but without the auto-generated tests)
* But, it also supports "State-less" tests, e.g. unit tests.
* It's an API for writing BDD tests (i.e. without the textual story telling)
* Hence, the BDD keyworks **given**, **when**, and **then** are methods in the API.
* Designed by the assumption that BDD is really about describing transitions between states in a state machine.
* Supports Selenium, Mockito, Hamcrest

### State-based tests

State-based testing is when you define the expected behavior of a system in terms of a state machine. That's just like model based testing. But in this framework you define the states as classes, and its actions as methods. The tests are the written as given-when-then rules.
The `given(S1.class).when(it->it.action()).then(S2.class)` syntax defines an expected transition from state S1 to S2 when the `action()` is performed. As the `action()` method is defined in the S1 class it can only be performed in state S1.

Example:
```java
given(LockedDoorState.class)
.when(it -> it.turnsKey())
.then(UnlockedDoorState.class);
```
For testing web applications:
```java
given(GoogleSearchPage.class)
.when(it -> it.searchesFor("Selenium"))
.then(GoogleResultPage.class)
.then(it -> it.hasTitle("Selenium"));
```

### Behavior expressions

You don't need to define a state machine to use the framework. While acceptance tests or end-to-end test may use state machines, your typical unit test won't. But the framework has behavior expressions, like this:
``` java
given(new BowlingGame())
.when(performing(the -> the.roll(1))).times(20)
.then(the -> the.score(), is(20));
```
Behavior expressions use Hamcrest matchers, as you can see in the example above (last line: the "is(20)").
As you can see you don't need to declare any local variables, and you need a lot less helper functions, since the code
reads well as it is.
Java 8 has a new feature called Streams, which also works nicely with the framework. This is an example with streams:
 ``` java
public class PokerTest {

  @Test
  public void shouldSortCardsAccordingToRank() {
      given(Stream.of("10H", "1H", "KD", "QS"))
      .when(transforming(it->it.map(Card::new).sorted()))
      .when(transforming(it->it.map(Card::toString).collect(toList())))
      .then(it(), is(equalTo(asList("1H", "10H", "QS", "KD"))));
  }
}
```
#### Syntax
Behavior expressions are really simple. You start with an object, the given(...). Then you perhaps transform it
to something else, with when(transforming(...)), possibly in several steps.
And lastly you verify the result, with a Hamcrest matcher in the then-clause:
``` java
given(<object>)
.when(transforming(....))
.then(it(), <matcher>)
```

Or perhaps you just perform operations on the objects, that will change its internal state, and verify the state lastly, in the then-clause:
``` java
given(<object>)
.when(performing(....))
.then(<function>, <matcher>)
```
Naturally, you may combine these any way you like. The main difference is that transforming() will give you a new object to operate on, and when(performing()) will keep the same object.

Also, the Hamcrest matcher is optional, if you call a boolean method (i.e. a predicate):
``` java
given(new Counter())
.then(it -> it.isStopped());
```
As you can see, Java 8 lambda expressions are used. But, since Java has Method references as well, you don't have to use lambda expressions. The above expression could have been written:
``` java
given(new Counter())
.then(Counter::isStopped);
```
And for example:
 ``` java
given(new Counter())
.when(performing(it -> it.start(2)))
.when(performing(it -> it.decrease()))
.then(it -> it.isStopped(), is(false));
 ```
 can be written:
 ``` java
given(new Counter())
.when(performing(it -> it.start(2)))
.when(performing(Counter::decrease))
.then(Counter::isStopped, is(false));
 ```
You may even write it:
``` java
given(new Counter()).and(2)
.when(performing(Counter::start))
.when(performing(Counter::decrease))
.then(Counter::isStopped, is(false));
 ```
You can also reuse a behavior expression in several test, e.g. to initialize several tests with the same "given" (or a parametrized), or just extract it for better readability:

``` java

@Test
public void shouldBeStoppedWhenStartedAndDecreasedToZero() {
    given(startedWith(3))
    .when(decreasing(3))
    .then(Counter::isStopped);
}

private BehaviorExpression<Counter> startedWith(int startval) {
    return given(new Counter()).and(startval)
            .when(performing(Counter::start));
}

private PerformingExpression<Counter> decreasing(int n) {
    return performing(Counter::decrease).times(n);
}

private BehaviorExpression<Counter> initial() {
    return
        given(new Counter());
}

```

### Usage
#### Gradle
```groovy

repositories {
    maven {
        url "http://dl.bintray.com/thogr/maven"
    }
}

dependencies {
    testCompile "com.github.thogr.bedede:bedede-core:0.4"
}
```

#### Define States as Java classes

```java
import static com.github.thogr.bedede.Bedede.*;

@InitialState
public class DoorLocked extends DoorState {

    @OnEntry
    public Expecting<BooleanCondition> shouldBeLocked() {
        return expecting(door.isLocked(), otherwise("Unexpected unlocked door"));
    }
}
```
The @OnEntry method is the guard that defines the entry condition that must be fulfilled when entering the state.

#### Define Entries to states

An Entry to a state defines the path to the state, as a `given().when().then()` transition.
By defining a `@DefaultEntry` in a state class you may access the state's default entry with the given(State.class) syntax.
But you may also use an entry explicitly, e.g `given(DoorUnlocked.byUnlockingWith(someKey)`
```java

public static class DoorUnlocked extends DoorState {

        @DefaultEntry
        public static Entry<DoorUnlocked> byUnlockingWithCorrectKey() {
            return byUnlockingWith(CORRECT_KEY);
        }

        public static final Entry<DoorUnlocked> byUnlockingWith(final Key key) {
            return entry(DoorUnlocked.class).as()
                    .given(DoorLocked.class)
                    .when(it -> it.turnsKey(key))
                    .then(DoorUnlocked.class);
        }

        @OnEntry
        public Expecting<BooleanCondition> shouldBeUnlocked() {
            return expecting(!door.isLocked(), otherwise("Unexpected locked door"));
        }
    }

```

#### JUnit Tests

```java

import static com.github.thogr.bedede.Bedede.*;

public class DoorExampleTest {
 //.....

    @Test
    public void shouldLockWithCorrectKey() {
        given(DoorUnlocked.class)
        .when(it -> it.turnsKey(CORRECT_KEY))
        .then(DoorLocked.class);
    }

    @Test
    public void shouldNotLockWithWrongKey() {
        given(DoorUnlocked.class)
        .when(it -> it.turnsKey(WRONG_KEY))
        .then(DoorUnlocked.class);
    }
}
```

### Links

[Uncle Bob: "The truth about BDD"](https://sites.google.com/site/unclebobconsultingllc/the-truth-about-bdd)
