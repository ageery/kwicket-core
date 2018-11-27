package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Button
import org.apache.wicket.model.IModel
import org.kwicket.core.component.config.ButtonConfig
import org.kwicket.core.component.config.IButtonConfig
import org.kwicket.core.component.dsl.ConfigurableComponentTag
import org.kwicket.core.component.factory.invoke

fun HTMLTag.button(
    id: String? = null,
    tagName: String = "button",
    model: IModel<String>? = null,
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
    onSubmit: (Button.() -> Unit)? = null,
    onError: (Button.() -> Unit)? = null,
    block: ButtonTag.() -> Unit = {}
): Unit =
    ButtonTag(
        id = id,
        tagName = tagName,
        config = ButtonConfig(
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
        onSubmit = onSubmit,
        onError = onError),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class ButtonTag(
    id: String? = null,
    tagName: String = "form",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IButtonConfig,
    factory: (String, IButtonConfig) -> Button = { cid, c -> c(cid) }
) : IButtonConfig by config,
    ConfigurableComponentTag<String, Button, IButtonConfig>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag