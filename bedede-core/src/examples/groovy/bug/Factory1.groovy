package bug

class Factory1 {
  def entry(@DelegatesTo(Entry) Closure c) {
    Entry e = new Entry()
    c.delegate = e
    return e
  }
}
