# Bedede
======
Uncle Bob said:
> *The Given/When/Then syntax of BDD seemed eerily familiar when I first heard about it several years ago. It’s been tickling at the back of my brain since then. Something about that triplet was trying to resonate with something else in my brain.*
> *This strange similarity caused me to realize that GIVEN/WHEN/THEN is simply a state transition, and that BDD is really just a way to describe a finite state machine.*

## A simple state based BDD framework for Java

**Note:**
Currently I'm experimenting a lot. So don't expect a stable API, just yet.

But, to give you an idea what Bedede is:

* It's State-based. (That's very much like Model-based testing, but without the auto-generated tests)
* It's a BDD-test framework. (But as a Java/Groovy API, i.e. without the textual story telling)
* Hence, the BDD keyworks **given**, **when**, and **then** are methods in the API.
* Designed by the assumption that BDD is really about describing transitions between states in a state machine.

The *given(S1.class).when(it->it.action()).then(S2.class)* syntax defines an expected transition from state S1 to S2 when the action is performed. As the *action()* is defined in the S1 class it can only be performed in state S1.

Example:
```java
 given(LockedDoorState.class)
.when(it -> it.turnsKey())
.then(UnlockedDoorState.class);
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
    testCompile "com.github.thogr.bedede:bedede-core:0.2"
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
An Entry to a state defines the path to the state, as a *given().when().then* transition.
By defining a @DefaultEntry in a state class you may access the states default entry with the given(State.class) syntax.
But you may also use an entry explicitly, e.g given(DoorUnlocked.byUnlockingWith(someKey)
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

#### Junit Tests

```java

import com.github.thogr.bedede.BehaviorDriven;

public class DoorExampleTest extends BehaviorDriven {
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
