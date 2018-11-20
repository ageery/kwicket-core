package org.kwicket.component.config

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.ajax.markup.html.AjaxLink
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.core.request.mapper.AbstractComponentMapper
import org.apache.wicket.markup.html.link.PopupSettings
import org.apache.wicket.model.IModel

interface IAjaxLinkConfig<T> : IComponentConfig<AjaxLink<T>, T> {
    var onClick: (AjaxLink<T>.(AjaxRequestTarget) -> Unit)?
}

class AjaxLinkConfig<T>(
    model: IModel<T>? = null,
    override var onClick: (AjaxLink<T>.(AjaxRequestTarget) -> Unit)? = null,
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
    onConfig: (AjaxLink<T>.() -> Unit)? = null,
    postInit: (AjaxLink<T>.() -> Unit)? = null
) : IAjaxLinkConfig<T>,
    ComponentConfig<AjaxLink<T>, T>(
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