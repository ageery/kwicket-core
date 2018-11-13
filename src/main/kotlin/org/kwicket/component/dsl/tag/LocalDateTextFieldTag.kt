package org.kwicket.component.dsl.tag

import kotlinx.html.*
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.config.ILocalDateTextFieldConfig
import org.kwicket.component.config.LocalDateTextFieldConfig
import org.kwicket.component.dsl.ComponentTag
import org.kwicket.component.factory.localDateTextFieldFactory
import java.time.LocalDate
import java.time.format.FormatStyle

private const val defaultTagName = "input"
private val defaultInputType = InputType.text

fun HTMLTag.localDateTextField(
    id: String? = null,
    tagName: String = defaultTagName,
    inputType: InputType? = defaultInputType,
    label: IModel<String>? = null,
    validator: IValidator<LocalDate>? = null,
    isRequired: Boolean? = null,
    validators: List<IValidator<LocalDate>>? = null,
    model: IModel<LocalDate>? = null,
    formatPattern: String? = null,
    parsePattern: String? = null,
    dateStyle: FormatStyle? = null,
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
    onConfig: (LocalDateTextField.() -> Unit)? = null,
    postInit: (LocalDateTextField.() -> Unit)? = null,
    block: LocalDateTextFieldTag.() -> Unit = {}
): Unit =
    LocalDateTextFieldTag(
        id = id,
        tagName = tagName,
        inputType = inputType,
        consumer = consumer,
        config = LocalDateTextFieldConfig(
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
            behaviors = behaviors,
            onConfig = onConfig,
            dateStyle = dateStyle,
            formatPattern = formatPattern,
            parsePattern = parsePattern,
            postInit = postInit
        )
    ).visit(block)

open class LocalDateTextFieldTag(
    id: String? = null,
    tagName: String = defaultTagName,
    inputType: InputType? = defaultInputType,
    consumer: TagConsumer<*>,
    val config: ILocalDateTextFieldConfig
) : ILocalDateTextFieldConfig by config,
    ComponentTag<LocalDateTextField>(
        id = id,
        initialAttributes = inputType.toAttr(),
        consumer = consumer,
        tagName = tagName
    ), HtmlBlockTag {

    override fun build(id: String) = localDateTextFieldFactory(id = id, config = config)

}