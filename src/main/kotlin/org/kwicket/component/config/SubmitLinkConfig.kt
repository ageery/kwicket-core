package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.markup.html.form.SubmitLink
import org.apache.wicket.model.IModel

interface ISubmitLinkConfig<T> : IComponentConfig<SubmitLink, T> {
    var form: Form<*>?
    var onSubmit: (SubmitLink.() -> Unit)?
    var onError: (SubmitLink.() -> Unit)?
    override val requiresSubclass: Boolean
        get() = true
}

class SubmitLinkConfig<T>(
    model: IModel<T>? = null,
    override var form: Form<*>? = null,
    override var onSubmit: (SubmitLink.() -> Unit)? = null,
    override var onError: (SubmitLink.() -> Unit)? = null,
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
    onConfig: (SubmitLink.() -> Unit)? = null,
    postInit: (SubmitLink.() -> Unit)? = null
) : ISubmitLinkConfig<T>,
    ComponentConfig<SubmitLink, T>(
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