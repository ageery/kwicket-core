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
import org.kwicket.component.config.IRadioChoiceConfig
import org.kwicket.component.config.RadioChoiceConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.radioChoiceFactory

fun <C: Any, T: C?> HTMLTag.radioChoice(
    id: String? = null,
    choices: IModel<List<C>>,
    choiceRenderer: IChoiceRenderer<C>? = null,
    tagName: String = "select",
    label: IModel<String>? = null,
    validator: IValidator<C>? = null,
    validators: List<IValidator<C>>? = null,
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
    block: RadioChoiceTag<C, T>.() -> Unit = {}
): Unit =
    RadioChoiceTag<C, T>(
        id = id,
        tagName = tagName,
        config = RadioChoiceConfig<C, T>(
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

fun <C: Any, T: C?> HTMLTag.radioChoice(
    id: String? = null,
    choices: IModel<List<C>>,
    choiceRenderer: IChoiceRenderer<C>,
    tagName: String = "select",
    label: IModel<String>? = null,
    validator: IValidator<C>? = null,
    validators: List<IValidator<C>>? = null,
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
    block: RadioChoiceTag<C, T>.() -> Unit = {}
): Unit =
    RadioChoiceTag<C, T>(
        id = id,
        tagName = tagName,
        config = RadioChoiceConfig<C, T>(
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

open class RadioChoiceTag<C: Any, T: C?>(
    id: String? = null,
    tagName: String = "span",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IRadioChoiceConfig<C, T>,
    factory: (String, IRadioChoiceConfig<C, T>) -> RadioChoice<C> = { cid, c -> radioChoiceFactory<C, T>(cid, c) }
) : IRadioChoiceConfig<C, T> by config,
    ConfigurableComponentTag<T, RadioChoice<C>, IRadioChoiceConfig<C, T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag