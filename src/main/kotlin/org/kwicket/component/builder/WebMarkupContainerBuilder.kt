package org.kwicket.component.builder

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.WebMarkupContainer
import org.apache.wicket.model.IModel
import org.kwicket.component.factory.webMarkupContainerFactory

interface IWebMarkupContainerBuilder<T> : IComponentBuilder<WebMarkupContainer, T>

class WebMarkupContainerBuilder<T>(
    model: IModel<T>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    enabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (WebMarkupContainer.() -> Unit)? = null,
    postInit: (WebMarkupContainer.() -> Unit)? = null
) : IWebMarkupContainerBuilder<T>,
    ComponentBuilder<WebMarkupContainer, T>(
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = visible,
        isVisibilityAllowed = visibilityAllowed,
        isEnabled = enabled,
        isEscapeModelStrings = escapeModelStrings,
        isRenderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    ) {

    override fun build(id: String): WebMarkupContainer =
        webMarkupContainerFactory(
            id = id,
            model = model,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            visible = isVisible,
            visibilityAllowed = isVisibilityAllowed,
            enabled = isEnabled,
            escapeModelStrings = isEscapeModelStrings,
            renderBodyOnly = isRenderBodyOnly,
            behavior = behavior,
            behaviors = behaviors,
            onConfig = onConfig,
            postInit = postInit
        )

}
