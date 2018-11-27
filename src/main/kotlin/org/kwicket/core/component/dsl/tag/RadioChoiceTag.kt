package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.IChoiceRenderer
import org.apache.wicket.markup.html.form.RadioChoice
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.core.component.config.IRadioChoiceConfig
import org.kwicket.core.component.config.RadioChoiceConfig
import org.kwicket.core.component.dsl.ConfigurableComponentTag
import org.kwicket.core.component.factory.invoke

fun <T> HTMLTag.radioChoice(
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
    block: RadioChoiceTag<T>.() -> Unit = {}
): Unit =
    RadioChoiceTag(
        id = id,
        tagName = tagName,
        config = RadioChoiceConfig(
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

open class RadioChoiceTag<T>(
    id: String? = null,
    tagName: String = "span",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IRadioChoiceConfig<T>,
    factory: (String, IRadioChoiceConfig<T>) -> RadioChoice<T> = { cid, c -> c(cid) }
) : IRadioChoiceConfig<T> by config,
    ConfigurableComponentTag<T, RadioChoice<T>, IRadioChoiceConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag