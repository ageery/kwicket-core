package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.select.Select
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.config.ISelectConfig
import org.kwicket.component.config.SelectConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.invoke

fun <C: Any, T: C?> HTMLTag.select(
    id: String? = null,
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
    block: SelectTag<C, T>.() -> Unit = {}
): Unit =
    SelectTag<C, T>(
        id = id,
        tagName = tagName,
        config = SelectConfig<C, T>(
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

fun <C: Any, T: C?> HTMLTag.select(
    id: String? = null,
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
    block: SelectTag<C, T>.() -> Unit = {}
): Unit =
    SelectTag<C, T>(
        id = id,
        tagName = tagName,
        config = SelectConfig<C, T>(
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

open class SelectTag<C: Any, T: C?>(
    id: String? = null,
    tagName: String = "select",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: ISelectConfig<C, T>,
    factory: (String, ISelectConfig<C, T>) -> Select<C> = { cid, c -> c<C, T>(cid) }
) : ISelectConfig<C, T> by config,
    ConfigurableComponentTag<T, Select<C>, ISelectConfig<C, T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag