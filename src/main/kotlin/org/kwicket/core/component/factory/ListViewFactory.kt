package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.link.Link
import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IListViewConfig

/**
 * Creates an [ListView] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [ListView]
 * @param id Wicket component id to use for the [ListView]
 * @receiver configuration for creating the [ListView]
 * @return [ListView] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T, L: List<T>> IListViewConfig<T, L>.invoke(id: String): ListView<T> {
    val onConfig = onConfig
    val stateless = stateless
    val populateItem = populateItem
    val model = model
    return object : ListView<T>(id, model) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        override fun populateItem(item: ListItem<T>) {
            populateItem?.invoke(item)
        }

    }.config(this)
}