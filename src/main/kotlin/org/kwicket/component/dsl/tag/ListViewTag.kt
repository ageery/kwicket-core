package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockInlineTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.model.IModel
import org.kwicket.component.builder.IComponentBuilder
import org.kwicket.component.builder.ListViewBuilder
import org.kwicket.component.dsl.ComponentTag

fun <T, L: List<T>> HTMLTag.listView(
    model: IModel<L>? = null,
    tagName: String = "div",
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
    onConfig: (ListView<T>.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    populateItem: (ListItem<T>.() -> Unit)? = null,
    block: ListViewTag<T, L>.() -> Unit = {}
): Unit =
    ListViewTag(
        id = id,
        tagName = tagName,
        model = model,
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
        populateItem = populateItem,
        consumer = consumer
    ).visit(block)

open class ListViewTag<T, L: List<T>>(
    id: String? = null,
    tagName: String = "span",
    model: IModel<L>? = null,
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
    onConfig: (ListView<T>.() -> Unit)? = null,
    postInit: (ListView<T>.() -> Unit)? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    populateItem: (ListItem<T>.() -> Unit)? = null,
    consumer: TagConsumer<*>
) : ComponentTag<ListView<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName),
    IComponentBuilder<ListView<T>, L> by ListViewBuilder<T, L>(
        model = model,
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
        postInit = postInit,
        populateItem = populateItem
    ),
    HtmlBlockInlineTag