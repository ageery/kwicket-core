package org.kwicket.component.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.model.IModel
import org.kwicket.component.wrapper.KWebMarkupContainer

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
    onConfig: (WebMarkupContainer.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: WebMarkupContainerTag.() -> Unit = {}
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
        onConfig = onConfig,
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class WebMarkupContainerTag(
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
    onConfig: (WebMarkupContainer.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>
) :
    ComponentTag<WebMarkupContainer>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        builder = { cid ->
            KWebMarkupContainer(
                id = cid,
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
                onConfig = onConfig
            )
        }), HtmlBlockTag