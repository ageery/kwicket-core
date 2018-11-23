package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.ZonedDateTimeField
import org.apache.wicket.model.IModel
import org.kwicket.component.config.IIZonedDateTimeFieldConfig
import org.kwicket.component.config.ZonedDateTimeFieldConfig
import org.kwicket.component.factory.invoke
import org.kwicket.component.q
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

fun <T: ZonedDateTime?> MarkupContainer.zonedDateTimeField(
    id: String,
    model: IModel<T>? = null,
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
    block: (IIZonedDateTimeFieldConfig<T>.() -> Unit)? = null
): ZonedDateTimeField = q(
    id = id,
    block = block,
    factory = { cid, config -> config(cid) },
    config = ZonedDateTimeFieldConfig(
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
