package com.github.thogr.bedede

import groovy.transform.*

@CompileStatic
class BehaviorDrivenExtension {
    static <T> Assuming<T> assuming(final BehaviorDriven self, final Class<T> state, @DelegatesTo(Assuming) Closure closure) {
        Assuming<T> result = self.assuming(state)
        closure.delegate = result
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure()
        return result
    }

    static <T> Assuming<T> given(final BehaviorDriven self, final Class<T> state, @DelegatesTo(Assuming) Closure closure) {
        Assuming<T> result = self.given(state)
        closure.delegate = result
        closure.resolveStrategy = Closure.DELEGATE_ONLY
        closure()
        return result
    }

}
