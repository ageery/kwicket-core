package org.kwicket.component.tag

import kotlinx.html.HTMLTag
import kotlinx.html.TagConsumer
import kotlinx.html.visitAndFinalize
import org.kwicket.wicketNamespacePrefix

internal fun RegionDescriptor.build(id: String) = BuilderPanel(id = id, body = this)

fun <T, C : TagConsumer<T>> C.panel(block: PANEL.() -> Unit = {}): T = PANEL(
    this
).visitAndFinalize(this, block)

class PANEL(consumer: TagConsumer<*>) : HTMLTag(
    tagName = "$wicketNamespacePrefix:panel",
    consumer = consumer,
    inlineTag = false,
    emptyTag = false,
    initialAttributes = emptyMap()
)
