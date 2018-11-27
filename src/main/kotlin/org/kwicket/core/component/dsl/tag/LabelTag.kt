package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.model.IModel
import org.kwicket.core.component.config.ILabelConfig
import org.kwicket.core.component.config.LabelConfig
import org.kwicket.core.component.dsl.ConfigurableComponentTag
import org.kwicket.core.component.factory.invoke

fun <T> HTMLTag.label(
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
    onConfig: (Label.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: LabelTag<T>.() -> Unit = {}
): Unit =
    LabelTag(
        id = id,
        tagName = tagName,
        config = LabelConfig(
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

open class LabelTag<T>(
    id: String? = null,
    tagName: String = "span",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: ILabelConfig<T>,
    factory: (String, ILabelConfig<T>) -> Label = { cid, c -> c(cid) }
) : ILabelConfig<T> by config,
    ConfigurableComponentTag<T, Label, ILabelConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag