package org.kwicket.component.config

import org.apache.wicket.behavior.Behavior
import org.apache.wicket.extensions.markup.html.repeater.data.sort.ISortStateLocator
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractToolbar
import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable
import org.apache.wicket.extensions.markup.html.repeater.data.table.IColumn
import org.apache.wicket.extensions.markup.html.repeater.data.table.ISortableDataProvider
import org.apache.wicket.markup.html.basic.Label

/**
 * Configuration for creating a [Label].
 *
 * @param T type of a row in the table
 * @param S type of the column sorts
 */
interface IDataTableConfig<T, S> : IComponentConfig<DataTable<T, S>, T> {
    var columns: List<IColumn<T, S>>?
    var dataProvider: ISortableDataProvider<T, S>?
    var topToolbars: (DataTable<T, S>.(ISortStateLocator<S>) -> List<AbstractToolbar>)?
    var bottomToolbars: (DataTable<T, S>.(ISortStateLocator<S>) -> List<AbstractToolbar>)?
    var rowsPerPage: Long
}

/**
 * Configuration for creating a [Label].
 *
 * @param T type of the model
 * @param model model for the component
 * @param markupId optional unique id to use in the associated markup
 * @param outputMarkupId whether to include an HTML id for the component in the markup
 * @param outputMarkupPlaceholderTag whether to include a placeholder tag for the component in the markup when the
 * component is not visible
 * @param isVisible whether the component is initially visible
 * @param isEnabled whether the component is initially enabled
 * @param isVisibilityAllowed whether the component is allowed to be visible
 * @param escapeModelStrings whether model strings should be escaped
 * @param renderBodyOnly whether the tag associated with the component should be included in the markup
 * @param behavior optional [Behavior] to add to the component
 * @param behaviors optional List of [Behavior]s to add to the component
 * @param stateless whether to include a hint that the component is stateless
 * @param onConfig optional lambda to execute in the onConfigure lifecycle method
 * @param postInit optional lambda to execute after the component has been created
 */
class DataTableConfig<T, S>(
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
    onConfig: (DataTable<T, S>.() -> Unit)? = null,
    postInit: (DataTable<T, S>.() -> Unit)? = null,
    override var columns: List<IColumn<T, S>>? = null,
    override var dataProvider: ISortableDataProvider<T, S>? = null,
    override var topToolbars: (DataTable<T, S>.(ISortStateLocator<S>) -> List<AbstractToolbar>)? = null,
    override var bottomToolbars: (DataTable<T, S>.(ISortStateLocator<S>) -> List<AbstractToolbar>)? = null,
    override var rowsPerPage: Long = 10
) : IDataTableConfig<T, S>,
    ComponentConfig<DataTable<T, S>, T>(
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