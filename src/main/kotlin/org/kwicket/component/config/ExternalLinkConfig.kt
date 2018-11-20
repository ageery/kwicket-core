package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.ExternalLink
import org.apache.wicket.markup.html.link.PopupSettings
import org.apache.wicket.model.IModel

interface IExternalLinkConfig : IComponentConfig<ExternalLink, String> {
    var label: IModel<*>?
    var popupSettings: PopupSettings?
}

class ExternalLinkConfig(
    model: IModel<String>? = null,
    override var label: IModel<*>? = null,
    override var popupSettings: PopupSettings? = null,
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
    onConfig: (ExternalLink.() -> Unit)? = null,
    postInit: (ExternalLink.() -> Unit)? = null
) : IExternalLinkConfig,
    ComponentConfig<ExternalLink, String>(
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