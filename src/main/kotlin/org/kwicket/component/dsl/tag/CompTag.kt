package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockInlineTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.Component
import org.kwicket.component.dsl.ComponentTag

fun <T : Component> HTMLTag.comp(
    tagName: String,
    comp: T,
    block: CompTag<T>.() -> Unit = {}
) = CompTag(
    tagName = tagName,
    comp = comp,
    consumer = consumer
).visit(block)

class CompTag<T : Component>(tagName: String, comp: T, consumer: TagConsumer<*>) :
    ComponentTag<T>(
        id = comp.id,
        initialAttributes = emptyMap(),
        consumer = consumer,
        tagName = tagName,
        comp = comp), HtmlBlockInlineTag {

    override fun build(id: String): T = comp!!
}