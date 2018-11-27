package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.select.Select
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.core.component.config.ISelectConfig
import org.kwicket.core.component.config.SelectConfig
import org.kwicket.core.component.dsl.ConfigurableComponentTag
import org.kwicket.core.component.factory.invoke

fun <T> HTMLTag.select(
    id: String? = null,
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
    block: SelectTag<T>.() -> Unit = {}
): Unit =
    SelectTag(
        id = id,
        tagName = tagName,
        config = SelectConfig(
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

open class SelectTag<T>(
    id: String? = null,
    tagName: String = "select",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: ISelectConfig<T>,
    factory: (String, ISelectConfig<T>) -> Select<T> = { cid, c -> c(cid) }
) : ISelectConfig<T> by config,
    ConfigurableComponentTag<T, Select<T>, ISelectConfig<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag