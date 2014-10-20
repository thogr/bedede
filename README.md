Bedede
======

Simple state based BDD framework for Java

Currently I'm experimenting a lot. So don't expect a stable API, just yet.

But, to give you an idea what Bedede is:

* It's State-based. (That's very much like Model-based testing, but without the auto-generated tests)
* It's a BDD-test framwork. (But as an Java/Groovy API, i.e. without the textual story telling) 
* The BDD keyworks given, when, then are methods in the API.
* BDD is really about describing transitions between states in a state machine.

Example:

 given(LockedDoorState.class)
.when(it -> it.turnsKey())
.then(UnlockedDoorState.class);




