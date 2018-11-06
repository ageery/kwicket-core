package org.kwicket.component.builder

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.feedback.IFeedbackMessageFilter
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.kwicket.component.factory.feedbackPanelFactory

interface IFeedbackPanelBuilder : IComponentBuilder<FeedbackPanel, Unit> {
    val filter: IFeedbackMessageFilter?
}

class FeedbackPanelBuilder(
    override val filter: IFeedbackMessageFilter? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    isVisible: Boolean? = null,
    isVisibilityAllowed: Boolean? = null,
    isEnabled: Boolean? = null,
    isEscapeModelStrings: Boolean? = null,
    isRenderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (FeedbackPanel.() -> Unit)? = null,
    postInit: (FeedbackPanel.() -> Unit)? = null
) : IFeedbackPanelBuilder,
    ComponentBuilder<FeedbackPanel, Unit>(
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = isVisible,
        isVisibilityAllowed = isVisibilityAllowed,
        isEnabled = isEnabled,
        escapeModelStrings = isEscapeModelStrings,
        renderBodyOnly = isRenderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    ) {

    override fun build(id: String): FeedbackPanel =
        feedbackPanelFactory(
            id = id,
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
