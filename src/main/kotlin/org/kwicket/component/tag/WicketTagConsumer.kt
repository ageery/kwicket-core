package org.kwicket.component.tag

import kotlinx.html.Entities
import kotlinx.html.Tag
import kotlinx.html.TagConsumer
import kotlinx.html.Unsafe
import kotlinx.html.consumers.onFinalizeMap
import kotlinx.html.stream.HTMLStreamBuilder
import org.kwicket.wicketIdAttr
import org.w3c.dom.events.Event

internal fun wicket(): TagConsumer<RegionDescriptor> = WicketTagConsumer(
    downstream = HTMLStreamBuilder(out = StringBuilder(), prettyPrint = false, xhtmlCompatible = true)
        .onFinalizeMap { sb, _ -> sb.toString() }
)

/**
 * [TagConsumer] for handling [ComponentBuilder] tags.
 */
internal class WicketTagConsumer(
    private val downstream: TagConsumer<String>,
    idStream: Sequence<String> = generateSequence(1) { it + 1 }.map { "$componentIdPrefix$it" }
) : TagConsumer<RegionDescriptor> {

    private var roots: MutableList<RegionItem> = mutableListOf()
    private var regionItem: RegionItem? = null
    private val idGenerator: Iterator<String> = idStream.iterator()

    override fun onTagStart(tag: Tag) {
        // FIXME: it feels like this could be replaced by using the functionality in ComponentBuilder
        // FIXME: could it almost be a ComponentBuilder.toRegionItem function?
        if (tag is ComponentBuilder<*>) {
            // FIXME: we could do this id stuff in the tag handling...
            val idHolder  = IdHolder(id = tag.id, idGenerator = idGenerator)
            val wicketId = tag.id?.let { it } ?: idGenerator.next()
            //val idHolder = { wicketId }
            val holder = tag.holder
            regionItem = (regionItem?.add(idHolder = idHolder, holder = holder)
                    ?: RegionItem(idHolder = idHolder, holder = holder))
                .also { if (it.parent == null) roots.add(it) }
            if (!tag.attributes.containsKey(wicketIdAttr)) tag.attributes[wicketIdAttr] = wicketId
        }
        downstream.onTagStart(tag)
    }

    override fun onTagAttributeChange(tag: Tag, attribute: String, value: String?) {
        downstream.onTagAttributeChange(tag = tag, attribute = attribute, value = value)
    }

    override fun onTagComment(content: CharSequence) {
        downstream.onTagComment(content)
    }

    override fun onTagEvent(tag: Tag, event: String, value: (Event) -> Unit) {
        downstream.onTagEvent(tag = tag, event = event, value = value)
    }

    override fun onTagEnd(tag: Tag) {
        downstream.onTagEnd(tag)
        if (tag is ComponentBuilder<*>) {
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