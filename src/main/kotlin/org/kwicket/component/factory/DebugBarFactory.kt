package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.devutils.debugbar.DebugBar
import org.kwicket.component.config

fun debugBarFactory(
    id: String,
    isInitiallyExpanded: Boolean = false,
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
    onConfig: (DebugBar.() -> Unit)? = null,
    postInit: (DebugBar.() -> Unit)? = null
): DebugBar =
    if (onConfig != null) {
        object : DebugBar(id, isInitiallyExpanded) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig.invoke(this)
            }

        }
    } else {
        DebugBar(id, isInitiallyExpanded)
    }.config(
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        enabled = enabled,
        visibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors
    ).also {
        postInit?.invoke(it)
    }