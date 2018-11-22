package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeTextField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import java.time.LocalDateTime
import java.time.format.FormatStyle

interface ILocalDateTimeTextFieldConfig<T: LocalDateTime?> : IFormComponentConfig<LocalDateTimeTextField, T> {
    var dateTimePattern: String?
    var dateStyle: FormatStyle?
    var timeStyle: FormatStyle?
}

class LocalDateTimeTextFieldConfig<T: LocalDateTime?>(
    model: IModel<T>? = null,
    override var dateTimePattern: String? = null,
    override var dateStyle: FormatStyle? = null,
    override var timeStyle: FormatStyle? = null,
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<T>? = null,
    validators: List<IValidator<T>>? = null,
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
    onConfig: (LocalDateTimeTextField.() -> Unit)? = null,
    postInit: (LocalDateTimeTextField.() -> Unit)? = null
) : ILocalDateTimeTextFieldConfig<T>,
    FormComponentConfig<LocalDateTimeTextField, T>(
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