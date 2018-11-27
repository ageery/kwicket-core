package org.kwicket.core.component.dsl

import org.apache.wicket.Component

interface WicketTag<T: Component> {
    val id: String?
    val comp: T?
    //val builder: ((String) -> T)?
    val isPreBuilt: Boolean
        get() = comp != null

    fun build(id: String): T

}