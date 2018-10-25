package org.kwicket.component.tag

import kotlinx.html.HTMLTag
import kotlinx.html.TagConsumer
import org.apache.wicket.Component

open class ComponentTag<T : Component>(
    override val id: String? = null,
    tagName: String,
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    override val builder: ((String) -> T)? = null,
    override val comp: T? = null
) : HTMLTag(
    tagName = tagName,
    consumer = consumer,
    inlineTag = false,
    emptyTag = false,
    initialAttributes = initialAttributes
), WicketTag<T>
