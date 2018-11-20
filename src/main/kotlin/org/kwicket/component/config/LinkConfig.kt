package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.link.PopupSettings
import org.apache.wicket.model.IModel

interface ILinkConfig<T> : IAbstractLinkConfig<Link<T>, T> {
    var onClick: (Link<T>.() -> Unit)?
    override val requiresSubclass: Boolean get() = true
}

open class LinkConfig<T>(
    model: IModel<T>? = null,
    override var onClick: (Link<T>.() -> Unit)? = null,
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
    onConfig: (Link<T>.() -> Unit)? = null,
    postInit: (Link<T>.() -> Unit)? = null
) : ILinkConfig<T>,
    AbstractLinkConfig<Link<T>, T>(
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