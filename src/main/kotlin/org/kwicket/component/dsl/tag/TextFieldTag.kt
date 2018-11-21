package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.config.ITextFieldConfig
import org.kwicket.component.config.TextFieldConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.invoke

fun <T: Any> HTMLTag.textField(
    id: String? = null,
    tagName: String = "input",
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
    initialAttributes: Map<String, String> = mapOf("type" to "text"),
    block: TextFieldTag<T, T>.() -> Unit = {}
): Unit =
    TextFieldTag(
        id = id,
        tagName = tagName,
        config = TextFieldConfig(
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

fun <T: Any> HTMLTag.textField(
    id: String? = null,
    tagName: String = "input",
    label: IModel<String>? = null,
    validator: IValidator<T>? = null,
    validators: List<IValidator<T>>? = null,
    isRequired: Boolean? = null,
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
    initialAttributes: Map<String, String> = mapOf("type" to "text"),
    block: TextFieldTag<T, T?>.() -> Unit = {}
): Unit =
    TextFieldTag<T, T?>(
        id = id,
        tagName = tagName,
        config = TextFieldConfig<T, T?>(
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

open class TextFieldTag<C : Any, T : C?>(
    id: String? = null,
    tagName: String = "input",
    initialAttributes: Map<String, String> = mapOf("type" to "text"),
    consumer: TagConsumer<*>,
    config: ITextFieldConfig<C, T>,
    factory: (String, ITextFieldConfig<C, T>) -> TextField<C> = { cid, c -> c.invoke<C, T>(cid) }
) : ITextFieldConfig<C, T> by config,
    ConfigurableComponentTag<T, TextField<C>, ITextFieldConfig<C, T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag