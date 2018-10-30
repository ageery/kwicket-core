package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.model.IModel
import org.kwicket.component.builder.IWebMarkupContainerBuilder
import org.kwicket.component.builder.WebMarkupContainerBuilder
import org.kwicket.component.dsl.ComponentTag

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
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        visibilityAllowed = visibilityAllowed,
        enabled = enabled,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class WebMarkupContainerTag<T>(
    id: String? = null,
    tagName: String = "div",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    val builder: WebMarkupContainerBuilder<T>
) : IWebMarkupContainerBuilder<T> by builder,
    ComponentTag<WebMarkupContainer>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName
    ), HtmlBlockTag {

    constructor(
        id: String? = null,
        tagName: String = "div",
        initialAttributes: Map<String, String> = emptyMap(),
        consumer: TagConsumer<*>,
        model: IModel<T>? = null,
        markupId: String? = null,
        outputMarkupId: Boolean? = null,
        outputMarkupPlaceholderTag: Boolean? = null,
        visible: Boolean? = null,
        visibilityAllowed: Boolean? = null,
        enabled: Boolean? = null,
        escapeModelStrings: Boolean? = null,
        renderBodyOnly: Boolean? = null,
        behavior: Behavior? = null,
        behaviors: List<Behavior>? = null
    ) : this(
        id = id,
        tagName = tagName,
        initialAttributes = initialAttributes,
        consumer = consumer,
        builder = WebMarkupContainerBuilder(
            model = model,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            visible = visible,
            visibilityAllowed = visibilityAllowed,
            enabled = enabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors
        )
    )

    override fun build(id: String) = builder.build(id)
}