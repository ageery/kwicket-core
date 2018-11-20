package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.apache.wicket.model.IModel
import org.kwicket.component.config.IListViewConfig
import org.kwicket.component.config.ListViewConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.listViewFactory

fun <T, L : List<T>> HTMLTag.listView(
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
        config = ListViewConfig(
            model = model,
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
            onConfig = onConfig,
            populateItem = populateItem
        ),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class ListViewTag<T, L : List<T>>(
    id: String? = null,
    tagName: String = "div",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IListViewConfig<T, L>,
    factory: (String, IListViewConfig<T, L>) -> ListView<T> = { cid, c -> listViewFactory(cid, c) }
) : IListViewConfig<T, L> by config,
    ConfigurableComponentTag<L, ListView<T>, IListViewConfig<T, L>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag