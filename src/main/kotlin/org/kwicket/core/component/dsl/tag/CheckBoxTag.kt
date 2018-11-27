package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.CheckBox
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.core.component.config.CheckBoxConfig
import org.kwicket.core.component.config.ICheckBoxConfig
import org.kwicket.core.component.dsl.ConfigurableComponentTag
import org.kwicket.core.component.factory.invoke

fun HTMLTag.checkBox(
    id: String? = null,
    tagName: String = "input",
    label: IModel<String>? = null,
    validator: IValidator<Boolean>? = null,
    validators: List<IValidator<Boolean>>? = null,
    model: IModel<Boolean>? = null,
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
    initialAttributes: Map<String, String> = mapOf("type" to "checkbox"),
    block: CheckBoxTag.() -> Unit = {}
): Unit =
    CheckBoxTag(
        id = id,
        tagName = tagName,
        config = CheckBoxConfig(
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
        behaviors = behaviors),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class CheckBoxTag(
    id: String? = null,
    tagName: String = "input",
    initialAttributes: Map<String, String> = mapOf("type" to "checkbox"),
    consumer: TagConsumer<*>,
    config: ICheckBoxConfig,
    factory: (String, ICheckBoxConfig) -> CheckBox = { cid, c -> c(cid) }
) : ICheckBoxConfig by config,
    ConfigurableComponentTag<Boolean, CheckBox, ICheckBoxConfig>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag