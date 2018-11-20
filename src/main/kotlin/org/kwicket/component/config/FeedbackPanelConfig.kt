package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.feedback.IFeedbackMessageFilter
import org.apache.wicket.markup.html.link.ExternalLink
import org.apache.wicket.markup.html.link.PopupSettings
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.apache.wicket.model.IModel

interface IFeedbackPanelConfig : IComponentConfig<FeedbackPanel, Unit> {
    var filter: IFeedbackMessageFilter?
}

class FeedbackPanelConfig(
    override var filter: IFeedbackMessageFilter? = null,
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
    onConfig: (FeedbackPanel.() -> Unit)? = null,
    postInit: (FeedbackPanel.() -> Unit)? = null
) : IFeedbackPanelConfig,
    ComponentConfig<FeedbackPanel, Unit>(
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