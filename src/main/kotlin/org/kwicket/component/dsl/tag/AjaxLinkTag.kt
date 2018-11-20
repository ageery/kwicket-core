package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.ajax.markup.html.AjaxLink
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.model.IModel
import org.kwicket.component.config.AjaxLinkConfig
import org.kwicket.component.config.IAjaxLinkConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.ajaxLinkFactory

fun <T> HTMLTag.ajaxLink(
    id: String? = null,
    tagName: String = "a",
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
    behaviors: List<Behavior>? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: AjaxLinkTag<T>.() -> Unit = {}
): Unit =
    AjaxLinkTag(
        id = id,
        tagName = tagName,
        config = AjaxLinkConfig(
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
        behaviors = behaviors),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class AjaxLinkTag<T>(
    id: String? = null,
    tagName: String = "a",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IAjaxLinkConfig<T>,
    factory: (String, IAjaxLinkConfig<T>) -> AjaxLink<T> = { cid, c -> ajaxLinkFactory(cid, c) }
) : IAjaxLinkConfig<T> by config,
    ConfigurableComponentTag<T, AjaxLink<T>, IAjaxLinkConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag