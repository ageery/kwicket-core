package org.kwicket.component.dsl.tag

import kotlinx.html.HTMLTag
import kotlinx.html.HtmlBlockTag
import kotlinx.html.TagConsumer
import kotlinx.html.visit
import org.apache.wicket.Page
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.kwicket.component.config.BookmarkablePageLinkConfig
import org.kwicket.component.config.IBookmarkablePageLinkConfig
import org.kwicket.component.dsl.ConfigurableComponentTag
import org.kwicket.component.factory.invoke
import kotlin.reflect.KClass

fun <P: Page> HTMLTag.bookmarkablePageLink(
    id: String? = null,
    page: KClass<P>? = null,
    pageParams: PageParameters? = null,
    tagName: String = "a",
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    enabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    initialAttributes: Map<String, String> = emptyMap(),
    block: BookmarkablePageLinkTag<*, P>.() -> Unit = {}
): Unit =
    BookmarkablePageLinkTag(
        id = id,
        tagName = tagName,
        config = BookmarkablePageLinkConfig<Any?, P>(
            page = page,
            pageParams = pageParams,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            isVisible = visible,
            isVisibilityAllowed = visibilityAllowed,
            isEnabled = enabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors
        ),
        initialAttributes = initialAttributes,
        consumer = consumer
    ).visit(block)

open class BookmarkablePageLinkTag<T, P: Page>(
    id: String? = null,
    tagName: String = "a",
    initialAttributes: Map<String, String> = emptyMap(),
    consumer: TagConsumer<*>,
    config: IBookmarkablePageLinkConfig<T, P>,
    factory: (String, IBookmarkablePageLinkConfig<T, P>) -> BookmarkablePageLink<T> = { cid, c -> c(cid) }
) : IBookmarkablePageLinkConfig<T, P> by config,
    ConfigurableComponentTag<T, BookmarkablePageLink<T>, IBookmarkablePageLinkConfig<T, P>>(
        id = id,
        initialAttributes = initialAttributes,
        consumer = consumer,
        tagName = tagName,
        config = config,
        factory = factory
    ), HtmlBlockTag