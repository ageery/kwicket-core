package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.IChoiceRenderer
import org.apache.wicket.markup.html.form.RadioChoice
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.builder.IRadioChoiceBuilder
import org.kwicket.component.builder.RadioChoiceBuilder
import org.kwicket.component.dsl.ComponentTag

fun <T> HTMLTag.radioChoice(
    id: String? = null,
    choices: IModel<List<T>>,
    choiceRenderer: IChoiceRenderer<T>? = null,
    tagName: String = "select",
    label: IModel<String>? = null,
    validator: IValidator<T>? = null,
    validators: List<IValidator<T>>? = null,
    model: IModel<T?>? = null,
    outputMarkupId: Boolean? = null,
    markupId: String? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    enabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: RadioChoiceTag<T>.() -> Unit = {}
): Unit =
    @Suppress("UNCHECKED_CAST")
    RadioChoiceTag(
        id = id,
        choices = choices,
        choiceRenderer = choiceRenderer,
        tagName = tagName,
        label = label,
        validator = validator,
        validators = validators,
        model = model as IModel<T>?,
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

fun <T> HTMLTag.radioChoice(
    id: String? = null,
    choices: IModel<List<T>>,
    choiceRenderer: IChoiceRenderer<T>,
    tagName: String = "select",
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
    initialAttributes: Map<String, String> = emptyMap(),
    block: RadioChoiceTag<T>.() -> Unit = {}
): Unit =
    RadioChoiceTag(
        id = id,
        choices = choices,
        choiceRenderer = choiceRenderer,
        tagName = tagName,
        label = label,
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

open class RadioChoiceTag<T>(
    id: String? = null,
    tagName: String = "span",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    val builder: RadioChoiceBuilder<T>
) : IRadioChoiceBuilder<T> by builder,
    ComponentTag<RadioChoice<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName
    ), HtmlBlockTag {

    constructor(
        id: String? = null,
        choices: IModel<List<T>>,
        choiceRenderer: IChoiceRenderer<T>? = null,
        label: IModel<String>? = null,
        validator: IValidator<T>? = null,
        validators: List<IValidator<T>>? = null,
        tagName: String = "select",
        initialAttributes: Map<String, String> = emptyMap(),
        consumer: TagConsumer<*>,
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
        behaviors: List<Behavior>? = null
    ) : this(
        id = id,
        tagName = tagName,
        initialAttributes = initialAttributes,
        consumer = consumer,
        builder = RadioChoiceBuilder(
            model = model,
            choices = choices,
            choiceRenderer = choiceRenderer,
            validators = validators,
            validator = validator,
            label = label,
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