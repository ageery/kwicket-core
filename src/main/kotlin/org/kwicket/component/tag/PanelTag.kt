package org.kwicket.component.tag

import kotlinx.html.TagConsumer
import kotlinx.html.visitAndFinalize
import org.apache.wicket.markup.html.panel.Panel

fun <T, C : TagConsumer<T>> C.panel(
    id: String? = null,
    tagName: String = "div",
    initialAttributes: Map<String, String> = emptyMap(),
    block: PanelTag.() -> Unit = {}
): T =
    PanelTag(
        id = id,
        tagName = tagName,
        initialAttributes = initialAttributes,
        consumer = this
    ).visitAndFinalize(this, block)

class PanelTag(
    id: String? = null,
    tagName: String = "div",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>
) : ComponentTag<Panel>(
    id = id,
    tagName = tagName,
    initialAttributes = initialAttributes,
    consumer = consumer
)