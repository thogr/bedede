package com.github.thogr.bedede;

class AssumingExtension {

    static <T, S> Then<S> then(Assuming<T> self, Class<S> state) {
        self.getController().then(state)
    }
}
