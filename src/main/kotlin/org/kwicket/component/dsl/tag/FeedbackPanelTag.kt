package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.feedback.IFeedbackMessageFilter
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.kwicket.component.config.FeedbackPanelConfig
import org.kwicket.component.config.IFeedbackPanelConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.invoke

fun HTMLTag.feedbackPanel(
    tagName: String = "div",
    filter: IFeedbackMessageFilter? = null,
    id: String? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    enabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (FeedbackPanel.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: FeedbackPanelTag.() -> Unit = {}
): Unit =
    FeedbackPanelTag(
        id = id,
        tagName = tagName,
        config = FeedbackPanelConfig(
            filter = filter,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            isVisible = visible,
            isVisibilityAllowed = visibilityAllowed,
            isEnabled = enabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors,
            onConfig = onConfig
        ),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class FeedbackPanelTag(
    id: String? = null,
    tagName: String = "div",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IFeedbackPanelConfig,
    factory: (String, IFeedbackPanelConfig) -> FeedbackPanel = { cid, c -> c(cid) }
) : IFeedbackPanelConfig by config,
    ConfigurableComponentTag<Unit, FeedbackPanel, IFeedbackPanelConfig>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag