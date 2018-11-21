package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.TextArea
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.config.ITextAreaConfig
import org.kwicket.component.config.TextAreaConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.invoke

fun <T: Any> HTMLTag.textArea(
    id: String? = null,
    cols: Int? = null,
    rows: Int? = null,
    tagName: String = "textarea",
    label: IModel<String>? = null,
    validator: IValidator<T>? = null,
    validators: List<IValidator<T>>? = null,
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
    initialAttributes: Map<String, String> = listOfNotNull(
        cols?.let { "cols" to cols.toString() },
        rows?.let { "rows" to rows.toString() }
    ).toMap(),
    block: TextAreaTag<T, T>.() -> Unit = {}
): Unit =
    TextAreaTag(
        id = id,
        tagName = tagName,
        config = TextAreaConfig(
            label = label,
            isRequired = true,
            validator = validator,
            validators = validators,
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

fun <T: Any> HTMLTag.textArea(
    id: String? = null,
    cols: Int? = null,
    rows: Int? = null,
    tagName: String = "textarea",
    label: IModel<String>? = null,
    validator: IValidator<T>? = null,
    isRequired: Boolean? = null,
    validators: List<IValidator<T>>? = null,
    model: IModel<T?>? = null,
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
    initialAttributes: Map<String, String> = listOfNotNull(
        cols?.let { "cols" to cols.toString() },
        rows?.let { "rows" to rows.toString() }
    ).toMap(),
    block: TextAreaTag<T, T?>.() -> Unit = {}
): Unit =
    TextAreaTag<T, T?>(
        id = id,
        tagName = tagName,
        config = TextAreaConfig<T, T?>(
            label = label,
            isRequired = isRequired,
            validator = validator,
            validators = validators,
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

open class TextAreaTag<C : Any, T: C?>(
    id: String? = null,
    tagName: String = "textarea",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: ITextAreaConfig<C, T>,
    factory: (String, ITextAreaConfig<C, T>) -> TextArea<C> = { cid, c -> c<C, T>(cid) }
) : ITextAreaConfig<C, T> by config,
    ConfigurableComponentTag<T, TextArea<C>, ITextAreaConfig<C, T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag