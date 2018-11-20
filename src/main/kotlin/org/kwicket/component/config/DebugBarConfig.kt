package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.devutils.debugbar.DebugBar
import org.apache.wicket.markup.html.form.Check
import org.apache.wicket.markup.html.form.CheckGroup
import org.apache.wicket.model.IModel

interface IDebugBarConfig : IComponentConfig<DebugBar, Unit> {
    var isInitiallyExpanded: Boolean?
}

class DebugBarConfig(
    override var isInitiallyExpanded: Boolean? = null,
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
    onConfig: (DebugBar.() -> Unit)? = null,
    postInit: (DebugBar.() -> Unit)? = null
) : IDebugBarConfig,
    ComponentConfig<DebugBar, Unit>(
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