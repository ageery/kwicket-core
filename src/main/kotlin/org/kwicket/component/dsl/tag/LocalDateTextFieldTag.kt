package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.InputType
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import org.kwicket.component.config.ILocalDateTextFieldConfig
import org.kwicket.component.config.LocalDateTextFieldConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.dsl.toAttr
import org.kwicket.component.factory.invoke
import java.time.LocalDate
import java.time.format.FormatStyle

private const val defaultTagName = "input"
private val defaultInputType = InputType.text

fun <T: LocalDate?> HTMLTag.localDateTextField(
    id: String? = null,
    tagName: String = defaultTagName,
    inputType: InputType? = defaultInputType,
    label: IModel<String>? = null,
    validator: IValidator<LocalDate>? = null,
    isRequired: Boolean? = null,
    validators: List<IValidator<LocalDate>>? = null,
    model: IModel<T>? = null,
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
    block: LocalDateTextFieldTag<T>.() -> Unit = {}
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

open class LocalDateTextFieldTag<T: LocalDate?>(
    id: String? = null,
    tagName: String = defaultTagName,
    inputType: InputType? = defaultInputType,
    consumer: TagConsumer<*>,
    config: ILocalDateTextFieldConfig<T>,
    factory: (String, ILocalDateTextFieldConfig<T>) -> LocalDateTextField = { cid, c -> c(cid) }
) : ILocalDateTextFieldConfig<T> by config,
    ConfigurableComponentTag<T, LocalDateTextField, ILocalDateTextFieldConfig<T>>(
        id = id,
        initialAttributes = inputType.toAttr(),
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag