package org.kwicket.core.component.builder

import org.apache.wicket.Component
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator
import org.apache.wicket.extensions.markup.html.repeater.data.table.AbstractColumn
import org.apache.wicket.markup.repeater.Item
import org.apache.wicket.model.IModel
import org.kwicket.core.component.config.AbstractColumnConfig
import org.kwicket.core.component.factory.invoke

fun <T, S> column(
    displayModel: IModel<String>? = null,
    sortProperty: S? = null,
    populateItem: (Item<ICellPopulator<T>>.(String, IModel<T>) -> Unit)? = null,
    header: ((String) -> Component)? = null,
    cssClasses: List<String>? = null,
    value: ((T) -> Any?)? = null
): AbstractColumn<T, S> =
    AbstractColumnConfig(
        displayModel = displayModel,
        sortProperty = sortProperty,
        populateItem = populateItem,
        header = header,
        cssClasses = cssClasses,
        value = value
    ).invoke()