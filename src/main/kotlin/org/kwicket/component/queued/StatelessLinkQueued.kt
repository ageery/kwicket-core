package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.StatelessLink
import org.kwicket.component.config.IStatelessLinkConfig
import org.kwicket.component.config.StatelessLinkConfig
import org.kwicket.component.factory.invoke
import org.kwicket.component.q

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
    block: (IStatelessLinkConfig<*>.() -> Unit)? = null
): StatelessLink<*> = q(
    id = id, block = block, factory = {cid, config -> config(cid)}, config = StatelessLinkConfig<Unit>(
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
