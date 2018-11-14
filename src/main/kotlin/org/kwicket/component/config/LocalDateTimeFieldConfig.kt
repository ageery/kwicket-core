package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeField
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeTextField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.FormatStyle

internal val IILocalDateTimeFieldConfig.useAnonSubClass: Boolean
    get() = onConfig != null || toLocalDate != null || toLocalTime != null || defaultTime != null

interface IILocalDateTimeFieldConfig : IFormComponentConfig<LocalDateTimeField, LocalDateTime> {
    var toLocalDate: ((LocalDateTime) -> LocalDate)?
    var toLocalTime: ((LocalDateTime) -> LocalTime)?
    var defaultTime: (() -> LocalTime)?
}

class LocalDateTimeFieldConfig(
    model: IModel<LocalDateTime>? = null,
    override var toLocalDate: ((LocalDateTime) -> LocalDate)? = null,
    override var toLocalTime: ((LocalDateTime) -> LocalTime)? = null,
    override var defaultTime: (() -> LocalTime)? = null,
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<LocalDateTime>? = null,
    validators: List<IValidator<LocalDateTime>>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    isVisible: Boolean? = null,
    isVisibilityAllowed: Boolean? = null,
    isEnabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (LocalDateTimeField.() -> Unit)? = null,
    postInit: (LocalDateTimeField.() -> Unit)? = null
) : IILocalDateTimeFieldConfig,
    FormComponentConfig<LocalDateTimeField, LocalDateTime>(
        model = model,
        label = label,
        isRequired = isRequired,
        validator = validator,
        validators = validators,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = isVisible,
        isVisibilityAllowed = isVisibilityAllowed,
        isEnabled = isEnabled,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    )