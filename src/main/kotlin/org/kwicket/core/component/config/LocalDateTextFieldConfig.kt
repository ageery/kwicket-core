package org.kwicket.core.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import java.time.LocalDate
import java.time.LocalTime
import java.time.format.FormatStyle

/**
 * Configuration for creating a [LocalDateTextField] component.
 *
 * @param T type of the model
 * @property formatPattern how to format the date/time object
 * @property parsePattern how to parse a date/time object
 * @property dateStyle style to use for formatting a date/time object
 */
interface ILocalDateTextFieldConfig<T: LocalDate?> : IFormComponentConfig<LocalDateTextField, T> {
    var formatPattern: String?
    var parsePattern: String?
    var dateStyle: FormatStyle?
}

/**
 * Configuration for creating a [LocalDateTimeField] component.
 *
 * @param T type of the model
 * @property formatPattern how to format the date/time object
 * @property parsePattern how to parse a date/time object
 * @property dateStyle style to use for formatting a date/time object
 * @param model model for the component
 * @param markupId optional unique id to use in the associated markup
 * @param outputMarkupId whether to include an HTML id for the component in the markup
 * @param outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
 * component is not visible
 * @param isVisible whether the component is initially visible
 * @param isEnabled whether the component is initially enabled
 * @param isVisibilityAllowed whether the component is allowed to be visible
 * @param escapeModelStrings whether model strings should be escaped
 * @param renderBodyOnly whether the tag associated with the component should be included in the markup
 * @param behavior optional [Behavior] to add to the component
 * @param behaviors optional List of [Behavior]s to add to the component
 * @param stateless whether to include a hint that the component is stateless
 * @param onConfig optional lambda to execute in the onConfigure lifecycle method
 * @param postInit optional lambda to execute after the component has been created
 */
class LocalDateTextFieldConfig<T: LocalDate?>(
    model: IModel<T>? = null,
    override var formatPattern: String? = null,
    override var parsePattern: String? = null,
    override var dateStyle: FormatStyle? = null,
    label: IModel<String>? = null,
    isRequired: Boolean? = null,
    validator: IValidator<in T>? = null,
    validators: List<IValidator<in T>>? = null,
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
    onConfig: (LocalDateTextField.() -> Unit)? = null,
    postInit: (LocalDateTextField.() -> Unit)? = null
) : ILocalDateTextFieldConfig<T>,
    FormComponentConfig<LocalDateTextField, T>(
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