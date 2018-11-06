package org.kwicket.component.factory

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.feedback.IFeedbackMessageFilter
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.kwicket.component.config

fun feedbackPanelFactory(
    id: String,
    filter: IFeedbackMessageFilter? = null,
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
    onConfig: (FeedbackPanel.() -> Unit)? = null,
    postInit: (FeedbackPanel.() -> Unit)? = null
): FeedbackPanel =
    if (onConfig != null) {
        object : FeedbackPanel(id, filter) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig.invoke(this)
            }

        }
    } else {
        FeedbackPanel(id, filter)
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
    ).also { postInit?.invoke(it) }