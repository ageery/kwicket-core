package org.kwicket.component.builder

import org.apache.wicket.Component

interface Builder<T: Component> {
    fun build(id: String): T
}