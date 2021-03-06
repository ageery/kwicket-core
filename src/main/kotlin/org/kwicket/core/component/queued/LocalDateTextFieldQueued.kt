package org.kwicket.core.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTextField
import org.apache.wicket.model.IModel
import org.kwicket.core.component.config.ILocalDateTextFieldConfig
import org.kwicket.core.component.config.LocalDateTextFieldConfig
import org.kwicket.core.component.factory.invoke
import org.kwicket.core.component.q
import java.time.LocalDate
import java.time.format.FormatStyle

fun <T: LocalDate?> MarkupContainer.localDateTextField(
    id: String,
    model: IModel<T>? = null,
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
    block: (ILocalDateTextFieldConfig<T>.() -> Unit)? = null
): LocalDateTextField = q(
    id = id, block = block, factory = { cid, config -> config(cid)}, config = LocalDateTextFieldConfig(
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