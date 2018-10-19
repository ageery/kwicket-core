package org.kwicket.component

import org.apache.wicket.Component
import org.apache.wicket.util.tester.WicketTester

internal fun WicketTester.render(component: Component, block: WicketTester.() -> Unit) {
    with(this) {
        startComponentInPage(component)
        block(this)
    }
}

internal val WicketTester.output: String
    get() = lastResponse.document