package org.kwicket.component.factory

import org.apache.wicket.markup.html.list.ListItem
import org.apache.wicket.markup.html.list.ListView
import org.kwicket.component.config
import org.kwicket.component.config.IListViewConfig

fun <T, L: List<T>> listViewFactory(
    id: String,
    config: IListViewConfig<T, L>
): ListView<T> {
    val onConfig = config.onConfig
    val stateless = config.stateless
    val populateItem = config.populateItem
    return object : ListView<T>(id, config.model) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun getStatelessHint(): Boolean =
            stateless ?: super.getStatelessHint()

        override fun populateItem(item: ListItem<T>) {
            populateItem?.invoke(item)
        }

    }.config(config)
}