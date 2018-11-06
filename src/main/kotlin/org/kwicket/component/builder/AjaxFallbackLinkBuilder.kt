package org.kwicket.component.builder

import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.model.IModel
import org.kwicket.component.factory.ajaxFallbackLinkFactory

interface IAjaxFallbackLinkBuilder<T> : IComponentBuilder<AjaxFallbackLink<T>, T> {
    var onClick: (AjaxFallbackLink<T>.(AjaxRequestTarget?) -> Unit)?
}

class AjaxFallbackLinkBuilder<T>(
    model: IModel<T>? = null,
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
    onConfig: (AjaxFallbackLink<T>.() -> Unit)? = null,
    override var onClick: (AjaxFallbackLink<T>.(AjaxRequestTarget?) -> Unit)? = null,
    postInit: (AjaxFallbackLink<T>.() -> Unit)? = null
) : IAjaxFallbackLinkBuilder<T>,
    ComponentBuilder<AjaxFallbackLink<T>, T>(
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
        onConfig = onConfig,
        postInit = postInit
    ) {

    override fun build(id: String): AjaxFallbackLink<T> =
        ajaxFallbackLinkFactory(
            id = id,
            model = model,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            visible = isVisible,
            visibilityAllowed = isVisibilityAllowed,
            enabled = isEnabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors,
            onConfig = onConfig,
            onClick = onClick,
            postInit = postInit
        )

}
