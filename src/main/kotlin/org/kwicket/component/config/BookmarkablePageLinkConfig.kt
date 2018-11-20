package org.kwicket.component.config

import org.apache.wicket.Page
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.model.IModel
import org.apache.wicket.request.mapper.parameter.PageParameters
import kotlin.reflect.KClass

interface IBookmarkablePageLinkConfig<T, P: Page> : IAbstractLinkConfig<BookmarkablePageLink<T>, T> {
    var page: KClass<P>?
    var pageParams: PageParameters?
//    override val requiresSubclass: Boolean
//        get() = onConfig != null
}

class BookmarkablePageLinkConfig<T, P: Page>(
    model: IModel<T>? = null,
    override var page: KClass<P>? = null,
    override var pageParams: PageParameters? = null,
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
    stateless: Boolean? = null,
    onConfig: (BookmarkablePageLink<T>.() -> Unit)? = null,
    postInit: (BookmarkablePageLink<T>.() -> Unit)? = null
) : IBookmarkablePageLinkConfig<T, P>,
    AbstractLinkConfig<BookmarkablePageLink<T>, T>(
        model = model,
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
        stateless = stateless,
        onConfig = onConfig,
        postInit = postInit
    )