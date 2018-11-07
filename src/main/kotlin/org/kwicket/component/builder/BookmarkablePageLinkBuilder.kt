package org.kwicket.component.builder

import org.apache.wicket.Page
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.markup.html.link.PopupSettings
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.kwicket.component.factory.bookmarkablePageLinkFactory
import kotlin.reflect.KClass

interface IBookmarkablePageLinkBuilder<P: Page> : IComponentBuilder<BookmarkablePageLink<*>, P> {
    val page: KClass<P>
    val pageParams: PageParameters?
    var popupSettings: PopupSettings?
}

class BookmarkablePageLinkBuilder<P: Page>(
    override val page: KClass<P>,
    override val pageParams: PageParameters? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    isVisible: Boolean? = null,
    isVisibilityAllowed: Boolean? = null,
    isEnabled: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (BookmarkablePageLink<*>.() -> Unit)? = null,
    override var popupSettings: PopupSettings? = null,
    postInit: (BookmarkablePageLink<*>.() -> Unit)? = null
) : IBookmarkablePageLinkBuilder<P>,
    ComponentBuilder<BookmarkablePageLink<*>, P>(
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        isVisible = isVisible,
        isVisibilityAllowed = isVisibilityAllowed,
        isEnabled = isEnabled,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors,
        onConfig = onConfig,
        postInit = postInit
    ) {

    override fun build(id: String): BookmarkablePageLink<*> =
        bookmarkablePageLinkFactory(
            page = page,
            pageParams = pageParams,
            id = id,
            markupId = markupId,
            outputMarkupId = outputMarkupId,
            outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
            visible = isVisible,
            visibilityAllowed = isVisibilityAllowed,
            enabled = isEnabled,
            escapeModelStrings = escapeModelStrings,
            renderBodyOnly = renderBodyOnly,
            behavior = behavior,
            behaviors = behaviors,
            onConfig = onConfig,
            postInit = postInit
        )

}
