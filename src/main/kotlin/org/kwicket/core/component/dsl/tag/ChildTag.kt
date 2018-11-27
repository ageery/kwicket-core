package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visitAndFinalize
import org.kwicket.core.wicketNamespacePrefix

fun <T, C : TagConsumer<T>> C.child(block: BODY.() -> Unit = {}): T = BODY(
    this
).visitAndFinalize(this, block)

class CHILD(consumer: TagConsumer<*>) : HTMLTag(
    tagName = "$wicketNamespacePrefix:child",
    consumer = consumer,
    inlineTag = true,
    emptyTag = true,
    initialAttributes = emptyMap()
), HtmlBlockTag
