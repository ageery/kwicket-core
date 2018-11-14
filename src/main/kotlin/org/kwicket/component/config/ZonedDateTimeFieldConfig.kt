package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.ZonedDateTimeField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

internal val IIZonedDateTimeFieldConfig.useAnonSubClass: Boolean
    get() = onConfig != null || toZonedDate != null || toZonedTime != null || defaultTime != null

interface IIZonedDateTimeFieldConfig : IFormComponentConfig<ZonedDateTimeField, ZonedDateTime> {
    var toZonedDate: ((ZonedDateTime) -> LocalDate)?
    var toZonedTime: ((ZonedDateTime) -> LocalTime)?
    var defaultTime: (() -> LocalTime)?
}

class ZonedDateTimeFieldConfig(
    model: IModel<ZonedDateTime>? = null,
    override var toZonedDate: ((ZonedDateTime) -> LocalDate)? = null,
    override var toZonedTime: ((ZonedDateTime) -> LocalTime)? = null,
    override var defaultTime: (() -> LocalTime)? = null,
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<ZonedDateTime>? = null,
    validators: List<IValidator<ZonedDateTime>>? = null,
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
    onConfig: (ZonedDateTimeField.() -> Unit)? = null,
    postInit: (ZonedDateTimeField.() -> Unit)? = null
) : IIZonedDateTimeFieldConfig,
    FormComponentConfig<ZonedDateTimeField, ZonedDateTime>(
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