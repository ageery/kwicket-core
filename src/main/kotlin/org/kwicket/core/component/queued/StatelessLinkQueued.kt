package org.kwicket.core.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.StatelessLink
import org.apache.wicket.model.IModel
import org.kwicket.core.component.config.IStatelessLinkConfig
import org.kwicket.core.component.config.StatelessLinkConfig
import org.kwicket.core.component.factory.invoke
import org.kwicket.core.component.q

fun MarkupContainer.statelessLink(
    id: String,
    onClick: (StatelessLink<*>.() -> Unit)? = null,
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
    onConfig: (StatelessLink<*>.() -> Unit)? = null,
    postInit: (StatelessLink<*>.() -> Unit)? = null,
    block: (IStatelessLinkConfig<Unit>.() -> Unit)? = null
): StatelessLink<Unit> = q(
    id = id, block = block, factory = {cid, config -> config(cid)}, config = StatelessLinkConfig(
        onClick = onClick,
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
        postInit = postInit
    )
)

fun <T> MarkupContainer.statelessLink(
    id: String,
    model: IModel<T>?,
    onClick: (StatelessLink<*>.() -> Unit)? = null,
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
    onConfig: (StatelessLink<*>.() -> Unit)? = null,
    postInit: (StatelessLink<*>.() -> Unit)? = null,
    block: (IStatelessLinkConfig<T>.() -> Unit)? = null
): StatelessLink<*> = q(
    id = id, block = block, factory = {cid, config -> config(cid)}, config = StatelessLinkConfig(
        model = model,
        onClick = onClick,
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
        postInit = postInit
    )
)
