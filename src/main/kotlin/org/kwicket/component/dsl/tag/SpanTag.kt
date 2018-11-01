package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.SPAN
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.Component
import org.kwicket.component.dsl.WicketTag
import org.kwicket.wicketIdAttr

fun <T : Component> HTMLTag.span(
    comp: T,
    block: SpanTag<T>.() -> Unit = {}
) = SpanTag(
    comp = comp,
    consumer = consumer
).visit(block)

class SpanTag<T : Component>(override val comp: T, consumer: TagConsumer<*>) :
    SPAN(initialAttributes = mapOf(wicketIdAttr to comp.id), consumer = consumer),
    WicketTag<T> {

    override val id: String = comp.id
    override fun build(id: String): T = comp

}