package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.model.IModel
import org.kwicket.component.config.IWebMarkupContainerConfig
import org.kwicket.component.config.WebMarkupContainerConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.webMarkupContainerFactory

fun HTMLTag.webMarkupContainer(
    id: String? = null,
    tagName: String = "div",
    model: IModel<*>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    enabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: WebMarkupContainerTag<*>.() -> Unit = {}
): Unit =
    WebMarkupContainerTag(
        id = id,
        tagName = tagName,
        config = WebMarkupContainerConfig(
            model = model,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            isVisible = visible,
            isVisibilityAllowed = visibilityAllowed,
            isEnabled = enabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors
        ),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class WebMarkupContainerTag<T>(
    id: String? = null,
    tagName: String = "div",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IWebMarkupContainerConfig<T>,
    factory: (String, IWebMarkupContainerConfig<T>) -> WebMarkupContainer = { cid, c ->
        webMarkupContainerFactory(cid, c)
    }
) : IWebMarkupContainerConfig<T> by config,
    ConfigurableComponentTag<T, WebMarkupContainer, IWebMarkupContainerConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag