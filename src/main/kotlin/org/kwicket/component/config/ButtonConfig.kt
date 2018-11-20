package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Button
import org.apache.wicket.markup.html.form.Check
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.model.IModel

interface IButtonConfig : IAbstractButtonConfig<Button> {
    var onSubmit: (Button.() -> Unit)?
    var onError: (Button.() -> Unit)?
}

class ButtonConfig(
    model: IModel<String>? = null,
    override var onSubmit: (Button.() -> Unit)? = null,
    override var onError: (Button.() -> Unit)? = null,
    defaultFormProcessing: Boolean? = null,
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
    onConfig: (Button.() -> Unit)? = null,
    postInit: (Button.() -> Unit)? = null
) : IButtonConfig,
    AbstractButtonConfig<Button, String>(
        model = model,
        defaultFormProcessing = defaultFormProcessing,
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