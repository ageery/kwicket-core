package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.StatelessLink
import org.kwicket.component.config.IStatelessLinkConfig
import org.kwicket.component.config.StatelessLinkConfig
import org.kwicket.component.factory.statelessLinkFactory
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
): StatelessLink<*> = statelessLink<Unit>(
    id = id, block = block, config = StatelessLinkConfig(
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
    config: IStatelessLinkConfig<T>,
    block: (IStatelessLinkConfig<T>.() -> Unit)? = null
): StatelessLink<T> {
    block?.invoke(config)
    return q(statelessLinkFactory(id = id, config = config))
}