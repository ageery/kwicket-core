package org.kwicket.core.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.devutils.debugbar.DebugBar
import org.kwicket.core.component.config.DebugBarConfig
import org.kwicket.core.component.config.IDebugBarConfig
import org.kwicket.core.component.dsl.ConfigurableComponentTag
import org.kwicket.core.component.factory.invoke

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
        tagName = tagName,
        config = DebugBarConfig(
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
        ),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class DebugBarTag(
    id: String? = null,
    tagName: String = "div",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IDebugBarConfig,
    factory: (String, IDebugBarConfig) -> DebugBar = { cid, c -> c(cid) }
) : IDebugBarConfig by config,
    ConfigurableComponentTag<Unit, DebugBar, IDebugBarConfig>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag