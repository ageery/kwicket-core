package org.kwicket.core.component.dsl

import kotlinx.html.HTMLTag
import kotlinx.html.TagConsumer
import org.apache.wicket.Component
import org.kwicket.core.component.config.IComponentConfig

abstract class ConfigurableComponentTag<M, T: Component, I: IComponentConfig<T, M>>(
    id: String? = null,
    tagName: String,
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    inlineTag: Boolean = false,
    emptyTag: Boolean = false,
    comp: T? = null,
    val config: I,
    val factory: (String, I) -> T
) : ComponentTag<T>(id = id, tagName = tagName, initialAttributes = initialAttributes,
    consumer = consumer, inlineTag = inlineTag, emptyTag = emptyTag, comp = comp) {

    override fun build(id: String): T = factory.invoke(id, config)

}

abstract class ComponentTag<T : Component>(
    override val id: String? = null,
    tagName: String,
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    inlineTag: Boolean = false,
    emptyTag: Boolean = false,
    override val comp: T? = null
) : HTMLTag(
    tagName = tagName,
    consumer = consumer,
    inlineTag = inlineTag,
    emptyTag = emptyTag,
    initialAttributes = initialAttributes
), WicketTag<T>
