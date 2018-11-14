package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeTextField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import java.time.LocalDateTime
import java.time.format.FormatStyle

// FIXME: can we just say: whatever the answer is true of the superclass + ...
internal val ILocalDateTimeTextFieldConfig.useAnonSubClass: Boolean
    get() = onConfig != null

interface ILocalDateTimeTextFieldConfig : IFormComponentConfig<LocalDateTimeTextField, LocalDateTime> {
    var dateTimePattern: String?
    var dateStyle: FormatStyle?
    var timeStyle: FormatStyle?
}

class LocalDateTimeTextFieldConfig(
    model: IModel<LocalDateTime>? = null,
    override var dateTimePattern: String? = null,
    override var dateStyle: FormatStyle? = null,
    override var timeStyle: FormatStyle? = null,
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
    onConfig: (LocalDateTimeTextField.() -> Unit)? = null,
    postInit: (LocalDateTimeTextField.() -> Unit)? = null
) : ILocalDateTimeTextFieldConfig,
    FormComponentConfig<LocalDateTimeTextField, LocalDateTime>(
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