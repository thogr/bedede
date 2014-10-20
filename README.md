# Bedede
======

## A simple state based BDD framework for Java

**Note:**
Currently I'm experimenting a lot. So don't expect a stable API, just yet.

But, to give you an idea what Bedede is:

* It's State-based. (That's very much like Model-based testing, but without the auto-generated tests)
* It's a BDD-test framework. (But as a Java/Groovy API, i.e. without the textual story telling) 
* Hence, the BDD keyworks **given**, **when**, and **then** are methods in the API.
* Designed by the assumption that BDD is really about describing transitions between states in a state machine.

Example:
```java
 given(LockedDoorState.class)
.when(it -> it.turnsKey())
.then(UnlockedDoorState.class);
```



