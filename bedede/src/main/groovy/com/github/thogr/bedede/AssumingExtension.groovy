package com.github.thogr.bedede;

class AssumingExtension {
    /*
    static <T, S> Then<S> then(Assuming<T> self, Class<S> state, Closure closure) {
        def result = self.controller.then(state)
        closure.delegate = result
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        return result
    }*/

    static <T, S> Then<S> then(Assuming<T> self, Class<S> state) {
        self.controller.then(state)
    }
}
