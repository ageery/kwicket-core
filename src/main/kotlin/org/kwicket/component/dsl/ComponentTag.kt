package org.kwicket.component.dsl

import kotlinx.html.HTMLTag
import kotlinx.html.TagConsumer
import org.apache.wicket.Component

abstract class ComponentTag<T : Component>(
    override val id: String? = null,
    tagName: String,
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    inlineTag: Boolean = false,
    emptyTag: Boolean = false,
    //override val builder: ((String) -> T)? = null, // = { println("-->$it"); comp!! }, //{ throw RuntimeException("Not implemented") },
    override val comp: T? = null
) : HTMLTag(
    tagName = tagName,
    consumer = consumer,
    inlineTag = inlineTag,
    emptyTag = emptyTag,
    initialAttributes = initialAttributes
), WicketTag<T>
