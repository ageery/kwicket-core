package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.TextArea
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.builder.ITextAreaBuilder
import org.kwicket.component.builder.ITextFieldBuilder
import org.kwicket.component.builder.TextAreaBuilder
import org.kwicket.component.builder.TextFieldBuilder
import org.kwicket.component.dsl.ComponentTag
import kotlin.reflect.KClass

fun <T: Any> HTMLTag.textArea(
    id: String? = null,
    tagName: String = "textarea",
    label: IModel<String>? = null,
    validator: IValidator<T>? = null,
    isRequired: Boolean? = null,
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
    initialAttributes: Map<String, String> = emptyMap(),
    block: TextAreaTag<T>.() -> Unit = {}
): Unit =
    @Suppress("UNCHECKED_CAST")
    TextAreaTag(
        id = id,
        tagName = tagName,
        label = label,
        isRequired = isRequired,
        validator = validator,
        validators = validators,
        model = model as IModel<T?>?,
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

fun <T: Any> HTMLTag.textArea(
    id: String? = null,
    cols: Int? = null,
    rows: Int? = null,
    tagName: String = "textarea",
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<T>? = null,
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
    block: TextAreaTag<T>.() -> Unit = {}
): Unit =
    TextAreaTag(
        id = id,
        tagName = tagName,
        label = label,
        isRequired = isRequired,
        validator = validator,
        validators = validators,
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

open class TextAreaTag<T: Any>(
    id: String? = null,
    tagName: String = "textarea",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    val builder: TextAreaBuilder<T>
) : ITextAreaBuilder<T> by builder,
    ComponentTag<TextArea<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName
    ), HtmlBlockTag {

    constructor(
        id: String? = null,
        label: IModel<String>? = null,
        isRequired: Boolean? = null,
        validator: IValidator<T>? = null,
        validators: List<IValidator<T>>? = null,
        tagName: String = "textarea",
        initialAttributes: Map<String, String> = emptyMap(),
        consumer: TagConsumer<*>,
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
        behaviors: List<Behavior>? = null
    ) : this(
        id = id,
        tagName = tagName,
        initialAttributes = initialAttributes,
        consumer = consumer,
        builder = TextAreaBuilder(
            model = model,
            validators = validators,
            validator = validator,
            label = label,
            isRequired = isRequired,
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
        )
    )

    override fun build(id: String) = builder.build(id)
}