package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visitAndFinalize
import org.kwicket.core.wicketNamespacePrefix

fun <T, C : TagConsumer<T>> C.body(block: BODY.() -> Unit = {}): T = BODY(
    this
).visitAndFinalize(this, block)

class BODY(consumer: TagConsumer<*>) : HTMLTag(
    tagName = "$wicketNamespacePrefix:body",
    consumer = consumer,
    inlineTag = true,
    emptyTag = true,
    initialAttributes = emptyMap()
), HtmlBlockTag
