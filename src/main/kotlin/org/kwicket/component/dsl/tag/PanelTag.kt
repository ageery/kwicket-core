package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visitAndFinalize
import org.kwicket.component.dsl.BuilderPanel
import org.kwicket.component.dsl.RegionDescriptor
import org.kwicket.component.dsl.wicket
import org.kwicket.wicketNamespacePrefix

internal fun RegionDescriptor.build(id: String) =
    BuilderPanel(id = id, body = this)

fun <T, C : TagConsumer<T>> C.panel(block: PANEL.() -> Unit = {}): T = PANEL(
    this
).visitAndFinalize(this, block)

internal fun panel(block: PANEL.() -> Unit = {}) = wicket().panel{ block.invoke(this) }
//)
//.visitAndFinalize(this, block)

class PANEL(consumer: TagConsumer<*>) : HTMLTag(
    tagName = "$wicketNamespacePrefix:panel",
    consumer = consumer,
    inlineTag = false,
    emptyTag = false,
    initialAttributes = emptyMap()
), HtmlBlockTag
