package org.kwicket.core.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.ZonedDateTimeField
import org.apache.wicket.model.IModel
import org.apache.wicket.validation.IValidator
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

/**
 * Whether the [IIZonedDateTimeFieldConfig] requires a sub-class to implement its properties.
 */
internal val IIZonedDateTimeFieldConfig<*>.requiresSubclass: Boolean
    get() = (this as IComponentConfig<*, *>).requiresSubclass || toZonedDate != null || toZonedTime != null || defaultTime != null

/**
 * Configuration for creating a [ZonedDateTimeField] component.
 *
 * @param T type of the component model
 * @property toZonedDate lambda for converting a [ZonedDateTime] to a [LocalDate]
 * @property toZonedTime lambda for converting a [ZonedDateTime] to a [LocalTime]
 * @property defaultTime default time to use for a date-only value
 */
interface IIZonedDateTimeFieldConfig<T : ZonedDateTime?> : IFormComponentConfig<ZonedDateTimeField, T> {
    var toZonedDate: ((ZonedDateTime) -> LocalDate)?
    var toZonedTime: ((ZonedDateTime) -> LocalTime)?
    var defaultTime: (() -> LocalTime)?
}

/**
 * Configuration for creating a [ZonedDateTimeField] component.
 *
 * @param T type of the model
 * @property toZonedDate lambda for converting a [ZonedDateTime] to a [LocalDate]
 * @property toZonedTime lambda for converting a [ZonedDateTime] to a [LocalTime]
 * @property defaultTime default time to use for a date-only value
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
class ZonedDateTimeFieldConfig<T : ZonedDateTime?>(
    model: IModel<T>? = null,
    override var toZonedDate: ((ZonedDateTime) -> LocalDate)? = null,
    override var toZonedTime: ((ZonedDateTime) -> LocalTime)? = null,
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
    onConfig: (ZonedDateTimeField.() -> Unit)? = null,
    postInit: (ZonedDateTimeField.() -> Unit)? = null
) : IIZonedDateTimeFieldConfig<T>,
    FormComponentConfig<ZonedDateTimeField, T>(
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