package org.kwicket.core.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.TimeField
import org.apache.wicket.model.IModel
import java.time.LocalTime

/**
 * Whether the configuration requires a subclass to implement.f
 */
internal val ITimeFieldConfig<*>.requiresSubclass: Boolean
    get() = (this as IComponentConfig<*, *>).requiresSubclass || use12HourFormat != null

/**
 * Configuration for creating a [TimeField] component.
 *
 * @param T type of the model
 * @property use12HourFormat whether to use 12-hour formatting for the time
 */
interface ITimeFieldConfig<T: LocalTime?> : IComponentConfig<TimeField, T> {
    var use12HourFormat: Boolean?
}

/**
 * Configuration for creating a [TimeField] component.
 *
 * @param T type of the model
 * @property use12HourFormat whether to use 12-hour formatting for the time
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
class TimeFieldConfig<T: LocalTime?>(
    model: IModel<T>? = null,
    override var use12HourFormat: Boolean? = null,
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
    onConfig: (TimeField.() -> Unit)? = null,
    postInit: (TimeField.() -> Unit)? = null
) : ITimeFieldConfig<T>,
    ComponentConfig<TimeField, T>(
        model = model,
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