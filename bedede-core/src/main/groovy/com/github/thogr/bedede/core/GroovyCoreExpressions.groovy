package com.github.thogr.bedede.core

import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

import com.github.thogr.bedede.core.internal.CoreExpressionsImpl

class GroovyCoreExpressions extends CoreExpressions {

    private static CoreExpressionsImpl impl = new CoreExpressionsImpl(new CoreExpressions())

    def private static current

    def static given(Map spec) {
        if (spec.a) {
            current = impl.given(CoreExpressions.a(spec.a))
            return current
        }
        throw new IllegalArgumentException("Syntax error at 'given'")
    }

    def static and(Map spec) {
        if (spec.a) {
            current = current.given(CoreExpressions.a(spec.a))
            return current
        }
    }

    def static when(Map spec) {
        if (spec.transforming) {
            current = current.when(CoreExpressions.transforming(context(spec.transforming)))
            return current
        }
        if (spec.performing) {
            def performing
            Closure closure = spec.performing
            if (closure.parameterTypes.length == 2) {
                performing = impl.performingBiAction(spec.performing)
            } else {
                performing = impl.performingAction(context(spec.performing))
            }
            if (spec.times) {
                current.when(performing).times(spec.times)
                return current
            }
            current.whenPerforming(performing)
            return current
        }

        throw new IllegalArgumentException("Syntax error at 'when'")
    }

    static then(Map spec, Matcher matcher=null) {
        if (spec.expect) {
            if (matcher) {
                return then(spec.expect, matcher)
            }
            return then(spec.expect, CoreMatchers.is(spec.is))
        }
        throw new IllegalArgumentException("Syntax error at 'then'")
    }

    static then(Closure closure, Matcher matcher) {
        if (closure.parameterTypes.length == 2) {
            current = current.thenTheyMatch(closure, matcher)
        } else {
            current = current.then(context(closure), matcher)
        }
        return current
    }

    static with(closure) {
        if (closure.parameterTypes.length == 2) {
            current = current.performBiAction(closure)
        } else {
            current = current.performAction(closure)
        }
    }

    static class Context {
        def the = null
        Closure closure

        Context(Closure c) {
            c.delegate = this
            this.closure = {
                the = it
                c(it)
            }
        }
    }

    static Closure context(Closure c) {
        return new Context(c).closure
    }
}
