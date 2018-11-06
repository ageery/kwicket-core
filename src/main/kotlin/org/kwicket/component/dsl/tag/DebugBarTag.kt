package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.devutils.debugbar.DebugBar
import org.kwicket.component.builder.DebugBarBuilder
import org.kwicket.component.builder.IDebugBarBuilder
import org.kwicket.component.dsl.ComponentTag

fun HTMLTag.debugBar(
    id: String? = null,
    isInitiallyExpanded: Boolean = false,
    tagName: String = "button",
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
    initialAttributes: Map<String, String> = emptyMap(),
    block: DebugBarTag.() -> Unit = {}
): Unit =
    DebugBarTag(
        id = id,
        isInitiallyExpanded = isInitiallyExpanded,
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
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class DebugBarTag(
    id: String? = null,
    tagName: String = "div",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    val builder: DebugBarBuilder
) : IDebugBarBuilder by builder,
    ComponentTag<DebugBar>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName
    ), HtmlBlockTag {

    constructor(
        id: String? = null,
        tagName: String = "div",
        initialAttributes: Map<String, String> = emptyMap(),
        consumer: TagConsumer<*>,
        isInitiallyExpanded: Boolean = false,
        markupId: String? = null,
        outputMarkupId: Boolean? = null,
        outputMarkupPlaceholderTag: Boolean? = null,
        visible: Boolean? = null,
        visibilityAllowed: Boolean? = null,
        enabled: Boolean? = null,
        escapeModelStrings: Boolean? = null,
        renderBodyOnly: Boolean? = null,
        behavior: Behavior? = null,
        behaviors: List<Behavior>? = null
    ) : this(
        id = id,
        tagName = tagName,
        initialAttributes = initialAttributes,
        consumer = consumer,
        builder = DebugBarBuilder(
            isInitiallyExpanded = isInitiallyExpanded,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            isVisible = visible,
            isVisibilityAllowed = visibilityAllowed,
            isEnabled = enabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors
        )
    )

    override fun build(id: String) = builder.build(id)
}