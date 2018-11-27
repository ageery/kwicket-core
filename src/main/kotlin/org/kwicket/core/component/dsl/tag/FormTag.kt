package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel
import org.kwicket.core.FileSize
import org.kwicket.core.component.config.FormConfig
import org.kwicket.core.component.config.IFormConfig
import org.kwicket.core.component.dsl.ConfigurableComponentTag
import org.kwicket.core.component.factory.invoke

fun <T> HTMLTag.form(
    id: String? = null,
    tagName: String = "form",
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
    isMultiPart: Boolean? = null,
    maxSize: FileSize? = null,
    fileMaxSize: FileSize? = null,
    block: FormTag<T>.() -> Unit = {}
): Unit =
    FormTag(
        id = id,
        tagName = tagName,
        config = FormConfig(
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
            fileMaxSize = fileMaxSize,
            maxSize = maxSize,
            isMultiPart = isMultiPart
        ),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class FormTag<T>(
    id: String? = null,
    tagName: String = "form",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IFormConfig<T>,
    factory: (String, IFormConfig<T>) -> Form<T> = { cid, c -> c(cid) }
) : IFormConfig<T> by config,
    ConfigurableComponentTag<T, Form<T>, IFormConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag