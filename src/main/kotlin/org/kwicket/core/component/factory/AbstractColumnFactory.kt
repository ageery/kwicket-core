package org.kwicket.core.component.factory

import org.apache.wicket.Component
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn
import org.apache.wicket.extensions.markup.html.repeater.data.table.LambdaColumn
import org.apache.wicket.markup.repeater.Item
import org.apache.wicket.model.IModel
import org.danekja.java.util.function.serializable.SerializableFunction
import org.kwicket.core.component.config.IAbstractColumnConfig
import org.kwicket.core.component.config.requiresSubclass

// row function is ugly!
/*
 * column(displayModel = "Name".res(), rowFunction = { it.name })
 */
operator fun <T, S> IAbstractColumnConfig<T, S>.invoke(): AbstractColumn<T, S> {
    val value = value
    val displayModel = displayModel
    val sort = sortProperty
    val cssClasses = cssClasses
    val populateItem = populateItem
    val requiresSubclass = requiresSubclass
    val header = header
    return if (value != null) {

        if (requiresSubclass) {
            object : LambdaColumn<T, S>(displayModel, sort, SerializableFunction<T, Any?> { value.invoke(it) }) {

                override fun populateItem(cellItem: Item<ICellPopulator<T>>, cid: String, rowModel: IModel<T>) {
                    populateItem?.invoke(cellItem, cid, rowModel) ?: super.populateItem(cellItem, cid, rowModel)
                }

                override fun getHeader(componentId: String): Component =
                    header?.invoke(componentId) ?: super.getHeader(componentId)

                override fun getCssClass(): String? = cssClasses?.joinToString(" ")

            }
        } else {
            LambdaColumn<T, S>(displayModel, sort, SerializableFunction<T, Any?> { value.invoke(it) })
        }
    } else {
        object : AbstractColumn<T, S>(displayModel, sort) {

            override fun populateItem(cellItem: Item<ICellPopulator<T>>, componentId: String, rowModel: IModel<T>) {
                populateItem?.invoke(cellItem, componentId, rowModel)
            }

            override fun getHeader(componentId: String): Component =
                header?.invoke(componentId) ?: super.getHeader(componentId)

            override fun getCssClass(): String? = cssClasses?.joinToString(" ")
        }
    }
}

