package org.kwicket.component.dsl

import kotlinx.html.HTMLTag
import org.kwicket.wicketIdAttr

var HTMLTag.wicketId: String?
    get() = attributes[wicketIdAttr]
    set(id) {
        if (id != null) {
            attributes[wicketIdAttr] = id
        } else {
            attributes.remove(wicketIdAttr)
        }
    }