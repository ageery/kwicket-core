package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeTextField
import org.apache.wicket.model.IModel
import org.kwicket.component.config.ILocalDateTimeTextFieldConfig
import org.kwicket.component.config.LocalDateTimeTextFieldConfig
import org.kwicket.component.factory.invoke
import org.kwicket.component.q
import java.time.LocalDateTime
import java.time.format.FormatStyle

fun <T: LocalDateTime?> MarkupContainer.localDateTimeTextField(
    id: String,
    model: IModel<T>? = null,
    dateTimePattern: String? = null,
    dateStyle: FormatStyle? = null,
    timeStyle: FormatStyle? = null,
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
    onConfig: (LocalDateTimeTextField.() -> Unit)? = null,
    postInit: (LocalDateTimeTextField.() -> Unit)? = null,
    block: (ILocalDateTimeTextFieldConfig<T>.() -> Unit)? = null
): LocalDateTimeTextField = q(
    id = id, block = block, factory = {cid, config -> config(cid) }, config = LocalDateTimeTextFieldConfig(
        model = model,
        dateTimePattern = dateTimePattern,
        dateStyle = dateStyle,
        timeStyle = timeStyle,
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
