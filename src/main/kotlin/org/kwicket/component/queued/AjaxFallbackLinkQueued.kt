package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.ajax.AjaxRequestTarget
import org.apache.wicket.ajax.markup.html.AjaxFallbackLink
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.model.IModel
import org.kwicket.component.config.AjaxFallbackLinkConfig
import org.kwicket.component.config.IAjaxFallbackLinkConfig
import org.kwicket.component.factory.ajaxFallbackLinkFactory

fun MarkupContainer.ajaxFallbackLink(
    id: String,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    enabled: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (AjaxFallbackLink<*>.() -> Unit)? = null,
    onClick: (AjaxFallbackLink<*>.(AjaxRequestTarget?) -> Unit)? = null,
    isStatelessHint: Boolean? = null,
    postInit: (AjaxFallbackLink<*>.() -> Unit)? = null,
    block: (IAjaxFallbackLinkConfig<*>.() -> Unit)? = null
) = q(
    id = id,
    block = block,
    factory = { cid, config -> ajaxFallbackLinkFactory(cid, config) },
    config = AjaxFallbackLinkConfig<Unit>(
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = visible,
        isEnabled = enabled,
        isVisibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        onClick = onClick,
        stateless = isStatelessHint,
        postInit = postInit
    )
)

fun <T> MarkupContainer.ajaxFallbackLink(
    id: String,
    model: IModel<T>? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    enabled: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (AjaxFallbackLink<T>.() -> Unit)? = null,
    onClick: (AjaxFallbackLink<T>.(AjaxRequestTarget?) -> Unit)? = null,
    isStatelessHint: Boolean? = null,
    postInit: (AjaxFallbackLink<T>.() -> Unit)? = null,
    block: (IAjaxFallbackLinkConfig<T>.() -> Unit)? = null
) = q(
    id = id,
    block = block,
    factory = { cid, config -> ajaxFallbackLinkFactory(cid, config) },
    config = AjaxFallbackLinkConfig(
        model = model,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = visible,
        isEnabled = enabled,
        isVisibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        onClick = onClick,
        stateless = isStatelessHint,
        postInit = postInit
    )
)