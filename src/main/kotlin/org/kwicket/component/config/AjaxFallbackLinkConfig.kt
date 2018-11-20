package org.kwicket.component.config

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.PopupSettings
import org.apache.wicket.model.IModel

interface IAjaxFallbackLinkConfig<T> : IAbstractLinkConfig<AjaxFallbackLink<T>, T> {
    var onClick: (AjaxFallbackLink<T>.(AjaxRequestTarget?) -> Unit)?
}

open class AjaxFallbackLinkConfig<T>(
    model: IModel<T>? = null,
    override var onClick: (AjaxFallbackLink<T>.(AjaxRequestTarget?) -> Unit)? = null,
    popupSettings: PopupSettings? = null,
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
    onConfig: (AjaxFallbackLink<T>.() -> Unit)? = null,
    postInit: (AjaxFallbackLink<T>.() -> Unit)? = null
) : IAjaxFallbackLinkConfig<T>,
    AbstractLinkConfig<AjaxFallbackLink<T>, T>(
        model = model,
        popupSettings = popupSettings,
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