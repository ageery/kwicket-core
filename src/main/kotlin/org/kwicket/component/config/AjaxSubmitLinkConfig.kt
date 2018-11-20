package org.kwicket.component.config

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.form.AjaxSubmitLink
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.form.Form
import org.apache.wicket.model.IModel

interface IAjaxSubmitLinkConfig<T> : IComponentConfig<AjaxSubmitLink, T> {
    var onSubmit: (AjaxSubmitLink.(AjaxRequestTarget) -> Unit)?
    var onError: (AjaxSubmitLink.(AjaxRequestTarget) -> Unit)?
    var form: Form<*>?
    override val requiresSubclass: Boolean
        get() = true
}

class AjaxSubmitLinkConfig<T>(
    model: IModel<T>? = null,
    override var onSubmit: (AjaxSubmitLink.(AjaxRequestTarget) -> Unit)? = null,
    override var onError: (AjaxSubmitLink.(AjaxRequestTarget) -> Unit)? = null,
    override var form: Form<*>? = null,
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
    onConfig: (AjaxSubmitLink.() -> Unit)? = null,
    postInit: (AjaxSubmitLink.() -> Unit)? = null
) : IAjaxSubmitLinkConfig<T>,
    ComponentConfig<AjaxSubmitLink, T>(
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