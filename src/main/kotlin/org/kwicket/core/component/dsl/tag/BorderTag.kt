package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visitAndFinalize
import org.kwicket.core.component.dsl.BuilderBorder
import org.kwicket.core.component.dsl.RegionDescriptor
import org.kwicket.core.component.dsl.wicket
import org.kwicket.core.wicketNamespacePrefix

internal fun RegionDescriptor.border(id: String) =
    BuilderBorder(id = id, body = this)

fun <T, C : TagConsumer<T>> C.border(block: BORDER.() -> Unit = {}): T = BORDER(
    this
).visitAndFinalize(this, block)

internal fun border(block: BORDER.() -> Unit = {}) = wicket().border{ block.invoke(this) }
//)
//.visitAndFinalize(this, block)

class BORDER(consumer: TagConsumer<*>) : HTMLTag(
    tagName = "$wicketNamespacePrefix:border",
    consumer = consumer,
    inlineTag = false,
    emptyTag = false,
    initialAttributes = emptyMap()
), HtmlBlockTag
