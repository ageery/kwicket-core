package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visitAndFinalize
import org.kwicket.component.dsl.wicket
import org.kwicket.wicketNamespacePrefix

fun <T, C : TagConsumer<T>> C.extend(block: EXTEND.() -> Unit = {}): T = EXTEND(
    this
).visitAndFinalize(this, block)

fun extend(block: EXTEND.() -> Unit = {}) = wicket().extend{ block.invoke(this) }
//)
//.visitAndFinalize(this, block)

class EXTEND(consumer: TagConsumer<*>) : HTMLTag(
    tagName = "$wicketNamespacePrefix:extend",
    consumer = consumer,
    inlineTag = false,
    emptyTag = false,
    initialAttributes = emptyMap()
), HtmlBlockTag
