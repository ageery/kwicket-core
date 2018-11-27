package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.basic.MultiLineLabel
import org.apache.wicket.model.IModel
import org.kwicket.core.component.config.IMultiLineLabelConfig
import org.kwicket.core.component.config.MultiLineLabelConfig
import org.kwicket.core.component.dsl.ConfigurableComponentTag
import org.kwicket.core.component.factory.invoke

fun <T> HTMLTag.multiLineLabel(
    model: IModel<T>? = null,
    tagName: String = "span",
    id: String? = null,
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
    onConfig: (MultiLineLabel.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: MultiLineLabelTag<T>.() -> Unit = {}
): Unit =
    MultiLineLabelTag(
        id = id,
        tagName = tagName,
        config = MultiLineLabelConfig(
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
            behaviors = behaviors,
            onConfig = onConfig
        ),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class MultiLineLabelTag<T>(
    id: String? = null,
    tagName: String = "span",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IMultiLineLabelConfig<T>,
    factory: (String, IMultiLineLabelConfig<T>) -> MultiLineLabel = { cid, c -> c(cid) }
) : IMultiLineLabelConfig<T> by config,
    ConfigurableComponentTag<T, MultiLineLabel, IMultiLineLabelConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag