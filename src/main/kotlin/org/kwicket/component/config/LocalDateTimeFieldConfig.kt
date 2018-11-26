package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeField
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeTextField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

/**
 * Whether the configuration requires a sub-class of [LocalDateTimeField] to implement
 */
internal val IILocalDateTimeFieldConfig<*>.requiresSubclass: Boolean
    get() = (this as IComponentConfig<*, *>).requiresSubclass || toLocalDate != null || toLocalTime != null || defaultTime != null

/**
 * Configuration for creating a [LocalDateTimeField] component.
 *
 * @param T type of the model
 * @property toLocalDate lambda for creating a [LocalDate] object
 * @property toLocalTime lambda for creating a [LocalTime] object
 * @property defaultTime default time to use when only the date is specified
 */
interface IILocalDateTimeFieldConfig<T: LocalDateTime?> : IFormComponentConfig<LocalDateTimeField, T> {
    var toLocalDate: ((LocalDateTime) -> LocalDate)?
    var toLocalTime: ((LocalDateTime) -> LocalTime)?
    var defaultTime: (() -> LocalTime)?
}

/**
 * Configuration for creating a [LocalDateTimeField] component.
 *
 * @param T type of the model
 * @property toLocalDate lambda for creating a [LocalDate] object
 * @property toLocalTime lambda for creating a [LocalTime] object
 * @property defaultTime default time to use when only the date is specified
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
class LocalDateTimeFieldConfig<T: LocalDateTime?>(
    model: IModel<T>? = null,
    override var toLocalDate: ((LocalDateTime) -> LocalDate)? = null,
    override var toLocalTime: ((LocalDateTime) -> LocalTime)? = null,
    override var defaultTime: (() -> LocalTime)? = null,
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
    onConfig: (LocalDateTimeField.() -> Unit)? = null,
    postInit: (LocalDateTimeField.() -> Unit)? = null
) : IILocalDateTimeFieldConfig<T>,
    FormComponentConfig<LocalDateTimeField, T>(
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