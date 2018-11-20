package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.ExternalLink
import org.apache.wicket.model.IModel
import org.kwicket.component.config.ExternalLinkConfig
import org.kwicket.component.config.IExternalLinkConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.externalLinkFactory

fun HTMLTag.externalLink(
    id: String? = null,
    tagName: String = "a",
    model: IModel<String>,
    label: IModel<*>? = null,
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
    block: ExternalLinkTag.() -> Unit = {}
): Unit =
    ExternalLinkTag(
        id = id,
        tagName = tagName,
        config = ExternalLinkConfig(
            label = label,
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

open class ExternalLinkTag(
    id: String? = null,
    tagName: String = "a",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IExternalLinkConfig,
    factory: (String, IExternalLinkConfig) -> ExternalLink = { cid, c -> externalLinkFactory(cid, c) }
) : IExternalLinkConfig by config,
    ConfigurableComponentTag<String, ExternalLink, IExternalLinkConfig>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag