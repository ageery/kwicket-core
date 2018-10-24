package org.kwicket.component.tag

import kotlinx.html.Tag
import org.apache.wicket.Component

/**
 * Auto-generated component id prefix.
 */
internal const val componentIdPrefix = "kw_"

/**
 * Region unique component id generator.
 */
internal typealias IdGenerator = Iterator<String>

/**
 * Provides a *lazy* way to get a *stable* component id.
 *
 * If the id is not null in the constructor, simply returns the id.
 *
 * If the id is null in the constructor, uses the [IdGenerator] to create a component id the first time the [id] is
 * accessed. Subsequent [id] access return the same value.
 *
 * @param id optional component id
 * @param idGenerator mechanism for generating a unique Wicket component id for a region
 *
 */
class IdHolder(id: String? = null, private val idGenerator: IdGenerator) {
    private var _id: String? = id

    /**
     * Returns the component id.
     */
    val id: String
        get() = _id ?: idGenerator.next().also { _id = it }
}

interface ComponentHolder<T : Component> {
    fun get(idHolder: IdHolder): T
}

interface WicketDslTag : Tag {
    // FIXME: do we really need this here or can we just do it in the tag consumer?
    val idGenerator: IdGenerator
}

class LambdaComponentHolder<T : Component>(private val builder: (String) -> T) : ComponentHolder<T> {
    private var _component: T? = null
    override fun get(idHolder: IdHolder): T =
        _component?.let { it } ?: builder.invoke(idHolder.id).also { _component = it }
}

class InstantiatedComponentHolder<T : Component>(private val component: T) : ComponentHolder<T> {
    override fun get(idHolder: IdHolder): T = component
}

