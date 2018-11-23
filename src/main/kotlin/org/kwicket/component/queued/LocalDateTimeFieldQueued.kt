package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeField
import org.apache.wicket.model.IModel
import org.kwicket.component.config.IILocalDateTimeFieldConfig
import org.kwicket.component.config.LocalDateTimeFieldConfig
import org.kwicket.component.factory.invoke
import org.kwicket.component.q
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun <T: LocalDateTime?> MarkupContainer.localDateTimeField(
    id: String,
    model: IModel<T>? = null,
    toLocalDate: ((LocalDateTime) -> LocalDate)? = null,
    toLocalTime: ((LocalDateTime) -> LocalTime)? = null,
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
    onConfig: (LocalDateTimeField.() -> Unit)? = null,
    postInit: (LocalDateTimeField.() -> Unit)? = null,
    block: (IILocalDateTimeFieldConfig<T>.() -> Unit)? = null
): LocalDateTimeField = q(
    id = id, block = block, factory = { cid, config -> config(cid) }, config = LocalDateTimeFieldConfig(
        model = model,
        toLocalDate = toLocalDate,
        toLocalTime = toLocalTime,
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
