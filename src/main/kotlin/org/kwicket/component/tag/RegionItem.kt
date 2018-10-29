package org.kwicket.component.tag

import org.apache.wicket.Component
import org.apache.wicket.MarkupContainer

/**
 * FIXME: change the description
 * Used by [WicketTagConsumer] to build a Wicket hierarchy.
 *
 * @property parent optional parent for the component builder
 * @property id Wicket id for the component to build
 * @property builder lambda for creating a Wicket component
 */
internal class RegionItem(
    val parent: RegionItem? = null,
    private val id: String,
    private val builder: (String) -> Component
) {

    private val children: MutableList<RegionItem> = mutableListOf()

    /**
     * Creates a [RegionItem] from the parameters and adds it as a child builder to the current builder.
     *
     * @param id Wicket id to use when creating the child component
     * @param builder lambda to use for creating the child Wicket component
     * @return the [RegionItem] constructed from the parameters
     */
    fun add(id: String, builder: (String) -> Component): RegionItem =
        RegionItem(parent = this, id = id, builder = builder)
            .also { children.add(it) }

    /**
     * Adds the Wicket [Component] created using the builder lambda to the [parent] and recursively adds the children
     * [Component]s to this [Component] as well.
     *
     * @param parent the Wicket parent in the component hierarchy
     */
    fun addTo(parent: MarkupContainer) {
        val c = builder.invoke(id)
        parent.add(c)
        children.forEach { if (c is MarkupContainer) it.addTo(c) else it.addTo(parent) }
    }

}