package com.github.thogr.bedede;

class AssumingExtension {

    static <T, S> Then<S> then(Assuming<T> self, Class<S> state) {
        self.controller.get().then(state)
    }
}
