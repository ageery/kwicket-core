package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.markup.html.form.IChoiceRenderer
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.config.DropDownChoiceConfig
import org.kwicket.component.config.IDropDownChoiceConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.dropDownChoiceFactory

fun <T: Any> HTMLTag.dropDownChoice(
    id: String? = null,
    choices: IModel<List<T>>,
    choiceRenderer: IChoiceRenderer<T>? = null,
    tagName: String = "select",
    label: IModel<String>? = null,
    validator: IValidator<T>? = null,
    validators: List<IValidator<T>>? = null,
    model: IModel<T>? = null,
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
    block: DropDownChoiceTag<T, T>.() -> Unit = {}
): Unit =
    DropDownChoiceTag(
        id = id,
        tagName = tagName,
        config = DropDownChoiceConfig(
            choices = choices,
            choiceRenderer = choiceRenderer,
            label = label,
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

fun <T: Any> HTMLTag.dropDownChoice(
    id: String? = null,
    choices: IModel<List<T>>,
    choiceRenderer: IChoiceRenderer<T>? = null,
    tagName: String = "select",
    label: IModel<String>? = null,
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
    isRequired: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: DropDownChoiceTag<T, T?>.() -> Unit = {}
): Unit =
    DropDownChoiceTag<T, T?>(
        id = id,
        tagName = tagName,
        config = DropDownChoiceConfig<T, T?>(
            choices = choices,
            isRequired = isRequired,
            choiceRenderer = choiceRenderer,
            label = label,
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

open class DropDownChoiceTag<C: Any, T: C?>(
    id: String? = null,
    tagName: String = "select",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IDropDownChoiceConfig<C, T>,
    factory: (String, IDropDownChoiceConfig<C, T>) -> DropDownChoice<C> = { cid, c -> dropDownChoiceFactory<C, T>(cid, c) }
) : IDropDownChoiceConfig<C, T> by config,
    ConfigurableComponentTag<T, DropDownChoice<C>, IDropDownChoiceConfig<C, T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag