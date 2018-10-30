package org.kwicket.component.dsl

import kotlinx.html.Entities
import kotlinx.html.Tag
import kotlinx.html.TagConsumer
import kotlinx.html.Unsafe
import kotlinx.html.consumers.DelayedConsumer
import kotlinx.html.consumers.onFinalizeMap
import kotlinx.html.stream.HTMLStreamBuilder
import org.kwicket.wicketIdAttr
import org.w3c.dom.events.Event

internal fun wicket(): TagConsumer<RegionDescriptor> =
    WicketTagConsumer(
        downstream = DelayedConsumer(HTMLStreamBuilder(
            out = StringBuilder(),
            prettyPrint = false,
            xhtmlCompatible = true
        )
            .onFinalizeMap { sb, _ -> sb.toString() }
        ))

/**
 * Auto-generated component id prefix.
 */
private const val componentIdPrefix = "kw_"

/**
 * [TagConsumer] for handling [Builder] tags.
 */
internal class WicketTagConsumer(
    private val downstream: TagConsumer<String>,
    idStream: Sequence<String> = generateSequence(1) { it + 1 }.map { "$componentIdPrefix$it" }
) : TagConsumer<RegionDescriptor> {

    private val sb: StringBuilder = StringBuilder()
    private var roots: MutableList<RegionItem> = mutableListOf()
    private var regionItem: RegionItem? = null
    private val idGenerator: Iterator<String> = idStream.iterator()

    /**
     * This function should do the following:
     * - determine the wicket id for the component
     * - create a new region item object
     * - if there is a previously created region item "in scope" add the newly created region item to it
     * - if there isn't a previously created region item "in scope" add the newly created region to the list of roots
     * - add the wicket id for the component to the map of attributes for the tag
     * - call the downstream tag handler to handle the actual HTML tag info
     *
     * First question: what does this method need in order to accomplish the above?
     * - id generator (does not need to leave this class)
     * - lambda for creating a Wicket component that takes an id
     * - or an already created component
     *
     * What happens when the component is added to the RegionItem -- what is stored? What is RegionItem?
     *
     */
    override fun onTagStart(tag: Tag) {
        if (tag is WicketTag<*>) {
            val (id, builder) =
                    if (tag.isPreBuilt) tag.comp!!.id to { _: String -> tag.comp!! }
                    else getId(tag, tag.attributes[wicketIdAttr]) to tag::build
            regionItem = regionItem?.add(id = id, builder = tag::build) ?:
                    RegionItem(parent = regionItem, id = id, builder = builder)
                        .also { if (it.parent == null) roots.add(it) }
            tag.attributes[wicketIdAttr] = id
        }
        downstream.onTagStart(tag)
    }

    private fun getId(tag: WicketTag<*>, attrId: String?): String =
        if (tag.isPreBuilt) tag.comp!!.id
        else tag.id ?: attrId ?: idGenerator.next()

    override fun onTagAttributeChange(tag: Tag, attribute: String, value: String?) {
        // FIXME: uncommenting the line below does not work...
        //downstream.onTagAttributeChange(tag = tag, attribute = attribute, value = value)
    }

    override fun onTagComment(content: CharSequence) {
        downstream.onTagComment(content)
    }

    override fun onTagEvent(tag: Tag, event: String, value: (Event) -> Unit) {
        downstream.onTagEvent(tag = tag, event = event, value = value)
    }

    override fun onTagEnd(tag: Tag) {
        downstream.onTagEnd(tag)
        if (tag is WicketTag<*>) {
            regionItem = regionItem?.parent
        }
    }

    override fun onTagContent(content: CharSequence) {
        downstream.onTagContent(content)
    }

    override fun onTagContentEntity(entity: Entities) {
        downstream.onTagContentEntity(entity)
    }

    override fun onTagContentUnsafe(block: Unsafe.() -> Unit) {
        downstream.onTagContentUnsafe(block)
    }

    override fun finalize(): RegionDescriptor =
        RegionDescriptor(markup = downstream.finalize(), builders = roots)
}