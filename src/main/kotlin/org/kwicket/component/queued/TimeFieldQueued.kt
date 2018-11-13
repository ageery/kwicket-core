package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.TimeField
import org.apache.wicket.model.IModel
import org.kwicket.component.config.ITimeFieldConfig
import org.kwicket.component.config.TimeFieldConfig
import org.kwicket.component.factory.timeFieldFactory
import org.kwicket.component.q
import java.time.LocalTime

fun MarkupContainer.timeField(
    id: String,
    model: IModel<LocalTime>? = null,
    use12HourFormat: Boolean? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    enabled: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (TimeField.() -> Unit)? = null,
    postInit: (TimeField.() -> Unit)? = null,
    block: (ITimeFieldConfig.() -> Unit)? = null
): TimeField = timeField(
    id = id, block = block, config = TimeFieldConfig(
        model = model,
        use12HourFormat = use12HourFormat,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = visible,
        isEnabled = enabled,
        isVisibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    )
)

fun MarkupContainer.timeField(
    id: String,
    config: ITimeFieldConfig,
    block: (ITimeFieldConfig.() -> Unit)? = null
): TimeField {
    block?.invoke(config)
    return q(timeFieldFactory(id = id, config = config))
}