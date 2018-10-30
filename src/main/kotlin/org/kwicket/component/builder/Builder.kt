package org.kwicket.component.builder

import org.apache.wicket.Component

// FIXME: what is a good name for this interface?
interface Builder<T: Component> {
    fun build(id: String): T
}