package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visitAndFinalize
import org.kwicket.core.component.dsl.BuilderPanel
import org.kwicket.core.component.dsl.RegionDescriptor
import org.kwicket.core.component.dsl.wicket
import org.kwicket.core.wicketNamespacePrefix

fun RegionDescriptor.create(id: String) =
    BuilderPanel(id = id, body = this)

fun <T, C : TagConsumer<T>> C.panel(block: PANEL.() -> Unit = {}): T = PANEL(
    this
).visitAndFinalize(this, block)

fun panel(block: PANEL.() -> Unit = {}) = wicket().panel{ block.invoke(this) }
//)
//.visitAndFinalize(this, block)

class PANEL(consumer: TagConsumer<*>) : HTMLTag(
    tagName = "$wicketNamespacePrefix:panel",
    consumer = consumer,
    inlineTag = false,
    emptyTag = false,
    initialAttributes = emptyMap()
), HtmlBlockTag
