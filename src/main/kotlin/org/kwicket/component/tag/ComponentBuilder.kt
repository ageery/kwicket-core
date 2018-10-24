package org.kwicket.component.tag

import org.apache.wicket.Component

/**
 * Defines how to create a Wicket [Component] from a  componentid.
 */
// FIXME: this all seems too complicated -- why can't we just use ComponentHolder here????
interface ComponentBuilder<T: Component> {
    val id: String?
    val holder: ComponentHolder<T>
    val idGenerator: IdGenerator

    val component: T
            get() = holder.get(IdHolder(id = id, idGenerator = idGenerator))

}

/**
 * Extends [ComponentBuilder] to provide the builder functionality based on an already-created [Component].
 */
interface PrebuiltComponentBuilder<T: Component> : ComponentBuilder<T> {
    val comp: T
    override val holder: ComponentHolder<T>
        get() = InstantiatedComponentHolder(comp)
    override val id: String?
        get() = comp.id
}
