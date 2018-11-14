package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.ZonedDateTimeField
import org.apache.wicket.model.IModel
import org.kwicket.component.config.IIZonedDateTimeFieldConfig
import org.kwicket.component.config.ZonedDateTimeFieldConfig
import org.kwicket.component.factory.zonedDateTimeFieldFactory
import org.kwicket.component.q
import java.time.*

fun MarkupContainer.zonedDateTimeField(
    id: String,
    model: IModel<ZonedDateTime>? = null,
    toZonedDate: ((ZonedDateTime) -> LocalDate)? = null,
    toZonedTime: ((ZonedDateTime) -> LocalTime)? = null,
    defaultTime: (() -> LocalTime)? = null,
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
    onConfig: (ZonedDateTimeField.() -> Unit)? = null,
    postInit: (ZonedDateTimeField.() -> Unit)? = null,
    block: (IIZonedDateTimeFieldConfig.() -> Unit)? = null
): ZonedDateTimeField = zonedDateTimeField(
    id = id, block = block, config = ZonedDateTimeFieldConfig(
        model = model,
        toZonedDate = toZonedDate,
        toZonedTime = toZonedTime,
        defaultTime = defaultTime,
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

fun MarkupContainer.zonedDateTimeField(
    id: String,
    config: IIZonedDateTimeFieldConfig,
    block: (IIZonedDateTimeFieldConfig.() -> Unit)? = null
): ZonedDateTimeField {
    block?.invoke(config)
    return q(zonedDateTimeFieldFactory(id = id, config = config))
}