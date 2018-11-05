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

fun <T> HTMLTag.listView(
    model: IModel<MutableList<T>>? = null,
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
    items: ListView<T>.(ListItem<T>) -> Unit,
    block: ListViewTag<T>.() -> Unit = {}
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
        items = items,
        consumer = consumer
    ).visit(block)

open class ListViewTag<T>(
    id: String? = null,
    tagName: String = "span",
    model: IModel<MutableList<T>>? = null,
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
    items: ListView<T>.(ListItem<T>) -> Unit,
    consumer: TagConsumer<*>
) : ComponentTag<ListView<T>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName),
    IComponentBuilder<ListView<T>, MutableList<T>> by ListViewBuilder<T>(
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
        items = items
    ),
    HtmlBlockInlineTag