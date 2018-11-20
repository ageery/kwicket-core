package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

interface IILocalDateTimeFieldConfig<T: LocalDateTime?> : IFormComponentConfig<LocalDateTimeField, LocalDateTime, T> {
    var toLocalDate: ((LocalDateTime) -> LocalDate)?
    var toLocalTime: ((LocalDateTime) -> LocalTime)?
    var defaultTime: (() -> LocalTime)?
    override val requiresSubclass: Boolean
        get() = super.requiresSubclass || toLocalDate != null || toLocalTime != null || defaultTime != null
}

class LocalDateTimeFieldConfig<T: LocalDateTime?>(
    model: IModel<T>? = null,
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
    stateless: Boolean? = null,
    onConfig: (LocalDateTimeField.() -> Unit)? = null,
    postInit: (LocalDateTimeField.() -> Unit)? = null
) : IILocalDateTimeFieldConfig<T>,
    FormComponentConfig<LocalDateTimeField, LocalDateTime, T>(
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
        stateless = stateless,
        onConfig = onConfig,
        postInit = postInit
    )