package org.kwicket.component.builder

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.devutils.debugbar.DebugBar
import org.kwicket.component.factory.debugBarFactory

interface IDebugBarBuilder : IComponentBuilder<DebugBar, Unit> {
    val isInitiallyExpanded: Boolean
}

class DebugBarBuilder(
    override val isInitiallyExpanded: Boolean = false,
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
    onConfig: (DebugBar.() -> Unit)? = null,
    postInit: (DebugBar.() -> Unit)? = null
) : IDebugBarBuilder,
    ComponentBuilder<DebugBar, Unit>(
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

    override fun build(id: String): DebugBar =
        debugBarFactory(
            id = id,
            isInitiallyExpanded = isInitiallyExpanded,
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
            postInit = postInit
        )

}
