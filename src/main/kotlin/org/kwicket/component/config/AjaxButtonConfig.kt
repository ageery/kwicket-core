package org.kwicket.component.config

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel

interface IAjaxButtonConfig : IAbstractButtonConfig<AjaxButton> {
    var onSubmit: (AjaxButton.(AjaxRequestTarget) -> Unit)?
    var onError: (AjaxButton.(AjaxRequestTarget) -> Unit)?
}

class AjaxButtonConfig(
    model: IModel<String>? = null,
    override var onSubmit: (AjaxButton.(AjaxRequestTarget) -> Unit)? = null,
    override var onError: (AjaxButton.(AjaxRequestTarget) -> Unit)? = null,
    defaultFormProcessing: Boolean? = null,
    form: Form<*>? = null,
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
    onConfig: (AjaxButton.() -> Unit)? = null,
    postInit: (AjaxButton.() -> Unit)? = null
) : IAjaxButtonConfig,
    AbstractButtonConfig<AjaxButton, String>(
        model = model,
        defaultFormProcessing = defaultFormProcessing,
        form = form,
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