package org.kwicket.core.component.dsl

import kotlinx.html.HTMLTag
import kotlinx.html.InputType
import org.kwicket.core.wicketIdAttr

var HTMLTag.wicketId: String?
    get() = attributes[wicketIdAttr]
    set(id) {
        if (id != null) {
            attributes[wicketIdAttr] = id
        } else {
            attributes.remove(wicketIdAttr)
        }
    }

fun InputType?.toAttr() = this?.let { mapOf("type" to it.realValue) } ?: emptyMap()