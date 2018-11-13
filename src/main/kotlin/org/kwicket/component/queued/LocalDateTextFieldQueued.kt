package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField
import org.apache.wicket.model.IModel
import org.kwicket.component.config.ILocalDateTextFieldConfig
import org.kwicket.component.config.LocalDateTextFieldConfig
import org.kwicket.component.q
import java.time.LocalDate
import java.time.format.FormatStyle

fun MarkupContainer.localDateTextField(
    id: String,
    model: IModel<LocalDate>? = null,
    formatPattern: String? = null,
    parsePattern: String? = null,
    dateStyle: FormatStyle? = null,
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
    onConfig: (LocalDateTextField.() -> Unit)? = null,
    postInit: (LocalDateTextField.() -> Unit)? = null,
    block: (ILocalDateTextFieldConfig.() -> Unit)? = null
): LocalDateTextField = localDateTextField(
    id = id, block = block, config = LocalDateTextFieldConfig(
        model = model,
        formatPattern = formatPattern,
        parsePattern = parsePattern,
        dateStyle = dateStyle,
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

fun MarkupContainer.localDateTextField(
    id: String,
    config: ILocalDateTextFieldConfig,
    block: (ILocalDateTextFieldConfig.() -> Unit)? = null
): LocalDateTextField {
    block?.invoke(config)
    return q(localDateTextField(id = id, config = config))
}