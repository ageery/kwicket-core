package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.TimeField
import org.apache.wicket.markup.html.media.video.Video
import org.apache.wicket.model.IModel
import java.time.LocalTime

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