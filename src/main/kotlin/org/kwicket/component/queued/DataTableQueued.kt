package org.kwicket.component.queued

import org.apache.wicket.MarkupContainer
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider
import org.apache.wicket.markup.html.basic.Label
import org.kwicket.component.config.DataTableConfig
import org.kwicket.component.config.IDataTableConfig
import org.kwicket.component.factory.invoke
import org.kwicket.component.q

/**
 * Creates and queues a [Label] into the parent container.
 *
 * @param id Wicket component id
 * @param model model for the component
 * @param markupId optional unique id to use in the associated markup
 * @param outputMarkupId whether to include an HTML id for the component in the markup
 * @param outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
 * component is not isVisible
 * @param visible whether the component is isVisible
 * @param enabled whether the component is isEnabled
 * @param visibilityAllowed whether the component is allowed to be isVisible
 * @param escapeModelStrings whether model strings should be escaped
 * @param renderBodyOnly whether the tag associated with the component should be included in the markup
 * @param behavior optional [Behavior] to add to the component
 * @param behaviors optional List of [Behavior]s to add to the component
 * @param onConfig optional lambda to execute in the onConfigure lifecycle method
 * @param block optional block to execute to configure the component
 * @return the created [Label] that has been queued into the parent container
 */
fun <T, S> MarkupContainer.dataTable(
    id: String,
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
    onConfig: (DataTable<T, S>.() -> Unit)? = null,
    postInit: (DataTable<T, S>.() -> Unit)? = null,
    columns: List<IColumn<T, S>>? = null,
    dataProvider: ISortableDataProvider<T, S>? = null,
    topToolbars: (DataTable<T, S>.(ISortStateLocator<S>) -> List<AbstractToolbar>)? = null,
    bottomToolbars: (DataTable<T, S>.(ISortStateLocator<S>) -> List<AbstractToolbar>)? = null,
    rowsPerPage: Long = 10,
    block: (IDataTableConfig<T, S>.() -> Unit)? = null
): DataTable<T, S> = q(id = id, block = block, factory = { cid, config -> config(cid) }, config =
    DataTableConfig(
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = visible,
        isEnabled = enabled,
        isVisibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit,
        columns = columns,
        dataProvider = dataProvider,
        topToolbars = topToolbars,
        bottomToolbars = bottomToolbars,
        rowsPerPage = rowsPerPage
    )
)