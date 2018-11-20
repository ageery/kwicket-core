package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.model.IModel
import org.kwicket.component.config.AjaxFallbackLinkConfig
import org.kwicket.component.config.IAjaxFallbackLinkConfig
import org.kwicket.component.dsl.ComponentTag
import org.kwicket.component.factory.ajaxFallbackLinkFactory

fun <T> HTMLTag.ajaxFallbackLink(
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
    block: AjaxFallbackLinkTag<T>.() -> Unit = {}
): Unit =
    AjaxFallbackLinkTag(
        id = id,
        tagName = tagName,
        config = AjaxFallbackLinkConfig(
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

open class AjaxFallbackLinkTag<T>(
    id: String? = null,
    tagName: String = "a",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    val config: IAjaxFallbackLinkConfig<T>
) : IAjaxFallbackLinkConfig<T> by config,
    ComponentTag<AjaxFallbackLink<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName
    ), HtmlBlockTag {
    override fun build(id: String) = ajaxFallbackLinkFactory(id = id, config = config)
}