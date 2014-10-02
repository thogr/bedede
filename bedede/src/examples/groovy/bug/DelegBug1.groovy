package bug

import groovy.transform.TypeChecked

/**
 * Factory2: This class is exactly the same as the Factory1
 * But Factory1 is defined in another file
 */
class Factory2 {
  static entry(@DelegatesTo(Entry) Closure c) {
    Entry e = new Entry()
    c.delegate = e
    return e
  }
}

@TypeChecked
class DelegBug1 {
  def works = Factory2.entry {
      boo() // <- This one works
  }

  def bug = new Factory1().entry {
      //boo() // <- Eclipse complains about this line:
      // Groovy:[Static type checking] -
      // Cannot find matching method bug.DelegBug1#boo().
      // Please check if the declared type is right and if the method exists.
  }
}
