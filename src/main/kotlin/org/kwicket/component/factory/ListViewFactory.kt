package org.kwicket.component.factory

import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.kwicket.component.config
import org.kwicket.component.config.IListViewConfig

/**
 * Creates a [ListView] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [ListView]
 * @param id Wicket component id
 * @param config specifies the settings for the [ListView] component
 * @return [ListView] with the Wicket component id of [id] and configured by [config]
 */
fun <T, L: List<T>> listViewFactory(id: String, config: IListViewConfig<T, L>): ListView<T> {
    val onConfig = config.onConfig
    val stateless = config.stateless
    val populateItem = config.populateItem
    return object : ListView<T>(id, config.model) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        override fun populateItem(item: ListItem<T>) {
            populateItem?.invoke(item)
        }

    }.config(config)
}