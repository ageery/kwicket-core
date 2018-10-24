package org.kwicket.component.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visitAndFinalize
import org.kwicket.wicketNamespacePrefix

fun <T, C : TagConsumer<T>> C.panel(block: PanelTag.() -> Unit = {}): T =
    PanelTag(this).visitAndFinalize(this, block)

fun <T, C : TagConsumer<T>> C.panel(block: PanelTag.() -> Unit = {}, consumer: TagConsumer<*>): T =
    PanelTag(consumer = consumer).visitAndFinalize(this, block)

class PanelTag(
    consumer: TagConsumer<*>,
    override val idGenerator: Iterator<String> = generateSequence(1) { it + 1 }
        .map { "$componentIdPrefix$it" }.iterator()
) : HTMLTag(
    tagName = "$wicketNamespacePrefix:panel",
    consumer = consumer,
    initialAttributes = emptyMap(),
    inlineTag = false,
    emptyTag = false
), HtmlBlockTag, WicketDslTag