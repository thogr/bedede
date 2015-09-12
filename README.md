# Bedede

## Bring BDD to your code

**Note:**
Currently I'm experimenting a lot. So don't expect a stable API, just yet.

To give you an idea what Bedede is:

* It's a BDD-test framework.
* But, with a fluent API test framework, not a framework for text based BDD specifications
* Hence, the BDD keyworks **given**, **when**, and **then** are methods in the API.
* The purpose is to make your test code more readable
* It supports unit tests
* It supports state-based acceptance tests.
* Supports Selenium, Mockito, Hamcrest

A problem with many unit tests is that it's hard to tell what is the prepare (given), and that is the
execution (when). Normally the test starts with a lot of local variable declarations, and some
method calls. This framework tries to solve this, making the tests read better.

### Behavior expressions
This is what behavior expressions looks like:
``` java
given(a(new BowlingGame()))
.when(performing(the -> the.roll(1))).times(20)
.then(the -> the.score(), is(20));
```
Behavior expressions use Hamcrest matchers, as you can see in the example above (last line: the "is(20)").
One thing about behavior expressions is that you don't need to declare any local variables, and you need a lot less helper functions, since the code reads well as it is.
``` java
 given(a(new Person())).with(it -> {
       it.setFirstName("John");
       it.setFamilyName("Smith");
 })
.when(retrieving(Person::getFullName))
.then(it(), is("John Smith"));
```
Java 8 has a new feature called Streams, which also works nicely with the framework. This is an example with streams:
 ``` java
import static com.github.thogr.bedede.Expressions.*;

public class PokerTest {

  @Test
  public void shouldSortCardsAccordingToRank() {
      given(a(Stream.of("10H", "1H", "KD", "QS")))
      .when(transforming(it->it.map(Card::new).sorted()))
      .when(transforming(it->it.map(Card::toString).collect(toList())))
      .then(it(), is(equalTo(asList("1H", "10H", "QS", "KD"))));
  }
}
```
#### Syntax
Behavior expressions are really simple. You start with an object, the given(...), possibly with at with()-clause to set it up. Then you perhaps transform it
to something else, with when(transforming(...)), or retrieve something from it with the when(retrieving(...)), possibly in several when-steps.
And lastly you verify the result, with a Hamcrest matcher in the then-clause:
``` java
given(a(<object>))
.when(transforming(....))
.when(retrieving(....))
.then(it(), <matcher>)
```
Of course you may have as many when() and then() as you like.
Or perhaps you just perform operations on the objects, that will change its internal state, and verify the state lastly, in the then-clause:
``` java
given(a(<object>))
.when(performing(....))
.then(<function>, <matcher>)
```
Naturally, you may combine these any way you like. The main difference is that transforming() will give you a new object to operate on, and when(performing()) will keep the same object.

Also, the Hamcrest matcher is optional, if you call a boolean method (i.e. a predicate):
``` java
given(a(new Counter()))
.then(it -> it.isStopped());
```
As you can see, Java 8 lambda expressions are used. But, since Java has Method references as well, you don't have to use lambda expressions. The above expression could have been written:
``` java
given(a(new Counter()))
.then(Counter::isStopped);
```
And for example:
 ``` java
given(a(new Counter()))
.when(performing(it -> it.start(2)))
.when(performing(it -> it.decrease()))
.then(it -> it.isStopped(), is(false));
 ```
 can be written:
 ``` java
given(a(new Counter()))
.when(performing(it -> it.start(2)))
.when(performing(Counter::decrease))
.then(Counter::isStopped, is(false));
 ```
You may even write it:
``` java
given(a(new Counter())).and(a(2))
.when(performing(Counter::start))
.when(performing(Counter::decrease))
.then(Counter::isStopped, is(false));
 ```
You can also reuse a behavior expression in several tests, e.g. to initialize several tests with the same "given" (or a parametrized one), or just extract it for better readability:

``` java

@Test
public void shouldBeStoppedWhenStartedAndDecreasedToZero() {
    given(startedWith(3))
    .when(decreasing(3))
    .then(Counter::isStopped);
}

private BehaviorExpression<Counter> startedWith(int startval) {
    return given(a(new Counter())).and(a(startval))
            .when(performing(Counter::start));
}

private Performing<Counter> decreasing(int n) {
    return performing(Counter::decrease).times(n);
}

private BehaviorExpression<Counter> initial() {
    return
        given(a(new Counter()));
}

```

### State-based tests
======
Uncle Bob said:
> *The Given/When/Then syntax of BDD seemed eerily familiar when I first heard about it several years ago. It’s been tickling at the back of my brain since then. Something about that triplet was trying to resonate with something else in my brain.*
> *This strange similarity caused me to realize that GIVEN/WHEN/THEN is simply a state transition, and that BDD is really just a way to describe a finite state machine.*

* It's designed by the assumption that BDD is really about describing transitions between states in a state machine.
* It's very much like Model-based testing, but without the auto-generated tests
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

#### Define States as Java classes

```java

import static com.github.thogr.bedede.Expressions.*;

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

#### Defaine state based cceptance tests as plain old JUnit Tests

```java

import static com.github.thogr.bedede.Expressions.*;

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

### Download
#### Using Gradle
```groovy

repositories {
    maven {
        url "http://dl.bintray.com/thogr/maven"
    }
}

dependencies {
    testCompile "com.github.thogr.bedede:bedede-lib:0.8.2"
}
```
### Links

[Uncle Bob: "The truth about BDD"](https://sites.google.com/site/unclebobconsultingllc/the-truth-about-bdd)
