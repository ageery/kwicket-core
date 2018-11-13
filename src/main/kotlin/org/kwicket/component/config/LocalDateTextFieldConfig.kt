package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import java.time.LocalDate
import java.time.format.FormatStyle

internal fun ILocalDateTextFieldConfig.useAnonSubClass() = onConfig != null

interface ILocalDateTextFieldConfig : IFormComponentConfig<LocalDateTextField, LocalDate> {
    var formatPattern: String?
    var parsePattern: String?
    var dateStyle: FormatStyle?
}

class LocalDateTextFieldConfig(
    model: IModel<LocalDate>? = null,
    override var formatPattern: String? = null,
    override var parsePattern: String? = null,
    override var dateStyle: FormatStyle? = null,
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<LocalDate>? = null,
    validators: List<IValidator<LocalDate>>? = null,
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
    onConfig: (LocalDateTextField.() -> Unit)? = null,
    postInit: (LocalDateTextField.() -> Unit)? = null
) : ILocalDateTextFieldConfig,
    FormComponentConfig<LocalDateTextField, LocalDate>(
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