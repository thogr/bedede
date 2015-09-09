package com.github.thogr.bedede

import org.hamcrest.CoreMatchers
import org.hamcrest.Matcher

import com.github.thogr.bedede.CoreExpressions;
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

    def static when(action) {
        current = current.when(action)
        return current
    }

    def static when(Map spec) {
        if (spec.transforming) {
            current = current.when(CoreExpressions.transforming(context(spec.transforming)))
            return current
        }
        if (spec.performing) {
            if (spec.times) {
                current.when(performing(spec.performing)).times(spec.times)
                return current
            }
            current.whenPerforming(performing(spec.performing))
            return current
        }

        throw new IllegalArgumentException("Syntax error at 'when'")
    }

    def static performing(closure) {
        if (closure.parameterTypes.length == 2) {
            return impl.performingBiAction(closure)
        } else {
            return impl.performingAction(context(closure))
        }
    }

    static then(Map spec, Matcher matcher=null) {
        if (spec.expect) {
            if (matcher) {
                return then(spec.expect, matcher)
            } else {
                if (spec.containsKey("is")) {
                    return then(spec.expect, CoreMatchers.is(spec.is))
                } else {
                    return then(spec.expect, CoreMatchers.is(true))
                }
            }
        }
        throw new IllegalArgumentException("Syntax error at 'then'")
    }

    static then(Closure closure, Matcher matcher=null) {
        if (matcher == null) {
            matcher = CoreMatchers.is(true)
        }
        if (closure.parameterTypes.length == 2) {
            current = current.thenTheyMatch(closure, matcher)
        } else {
            current = current.thenItMatches(context(closure), matcher)
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
