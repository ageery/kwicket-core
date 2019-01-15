package org.kwicket.core.component.config

import org.apache.wicket.Component
import org.apache.wicket.extensions.markup.html.repeater.data.grid.ICellPopulator
import org.apache.wicket.markup.repeater.Item
import org.apache.wicket.model.IModel

internal val IAbstractColumnConfig<*, *>.requiresSubclass: Boolean
    get() = populateItem != null || header != null || cssClasses != null

interface IAbstractColumnConfig<T, S> {
    var displayModel: IModel<String>?
    var sortProperty: S?
    var populateItem: (Item<ICellPopulator<T>>.(String, IModel<T>) -> Unit)?
    var header: ((String) -> Component)?
    var cssClasses: List<String>?
    var value: ((T) -> Any?)? // FIXME: should we make this a sub-class?
}

open class AbstractColumnConfig<T, S>(
    override var displayModel: IModel<String>? = null,
    override var sortProperty: S? = null,
    override var header: ((String) -> Component)? = null,
    override var cssClasses: List<String>? = null,
    override var value: ((T) -> Any?)? = null,
    override var populateItem: (Item<ICellPopulator<T>>.(String, IModel<T>) -> Unit)? = null
): IAbstractColumnConfig<T, S>