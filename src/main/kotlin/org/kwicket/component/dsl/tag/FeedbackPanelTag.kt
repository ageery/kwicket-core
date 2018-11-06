package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockInlineTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.feedback.IFeedbackMessageFilter
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.kwicket.component.builder.FeedbackPanelBuilder
import org.kwicket.component.builder.IFeedbackPanelBuilder
import org.kwicket.component.dsl.ComponentTag

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
        filter = filter,
        tagName = tagName,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        visibilityAllowed = visibilityAllowed,
        enabled = enabled,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class FeedbackPanelTag(
    id: String? = null,
    filter: IFeedbackMessageFilter? = null,
    tagName: String = "span",
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
    postInit: (FeedbackPanel.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>
) : ComponentTag<FeedbackPanel>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        emptyTag = true),
    IFeedbackPanelBuilder by FeedbackPanelBuilder(
        filter = filter,
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = visible,
        isVisibilityAllowed = visibilityAllowed,
        isEnabled = enabled,
        isEscapeModelStrings = escapeModelStrings,
        isRenderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    ),
    HtmlBlockInlineTag