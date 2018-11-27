package org.kwicket.core.component.factory

import org.apache.wicket.extensions.markup.html.repeater.data.table.DataTable
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IDataTableConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates an [DataTable] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the rows in the table
 * @param S type of sorts for the columns
 * @param id Wicket component id to use for the [DataTable]
 * @receiver configuration for creating the [DataTable]
 * @return [DataTable] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T, S> IDataTableConfig<T, S>.invoke(id: String): DataTable<T, S> {
    val columns = columns!!
    val dataProvider = dataProvider!!
    val rowsPerPage = rowsPerPage
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : DataTable<T, S>(id, columns, dataProvider, rowsPerPage) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        DataTable<T, S>(id, columns, dataProvider, rowsPerPage)
    }.config(this)
        .also { dataTable ->
            topToolbars?.let {
                it.invoke(dataTable, dataProvider).forEach { toolbar -> dataTable.addTopToolbar(toolbar) }
            }
            bottomToolbars?.let {
                it.invoke(dataTable, dataProvider).forEach { toolbar -> dataTable.addBottomToolbar(toolbar) }
            }
        }

}