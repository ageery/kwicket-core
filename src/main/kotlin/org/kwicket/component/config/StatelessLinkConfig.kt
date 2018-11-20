package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.link.StatelessLink

interface IStatelessLinkConfig<T> : IAbstractLinkConfig<StatelessLink<T>, T> {
    var onClick: (StatelessLink<T>.() -> Unit)?
    override val requiresSubclass: Boolean get() = true
}

class StatelessLinkConfig<T>(
    override var onClick: (StatelessLink<T>.() -> Unit)? = null,
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
    onConfig: (StatelessLink<T>.() -> Unit)? = null,
    postInit: (StatelessLink<T>.() -> Unit)? = null
) : IStatelessLinkConfig<T>,
    AbstractLinkConfig<StatelessLink<T>, T>(
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