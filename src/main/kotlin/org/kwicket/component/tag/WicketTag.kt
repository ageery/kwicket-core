package org.kwicket.component.tag

import org.apache.wicket.Component

interface WicketTag<T: Component> {
    val id: String?
    val builder: ((String) -> T)?
    val comp: T?
    val isPreBuilt: Boolean
        get() = comp != null
}