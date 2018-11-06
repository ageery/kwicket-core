package org.kwicket.component.factory

import org.apache.wicket.Page
import org.apache.wicket.behavior.Behavior
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.apache.wicket.request.mapper.parameter.PageParameters
import org.kwicket.component.config
import kotlin.reflect.KClass

fun <P : Page> bookmarkablePageLinkFactory(
    id: String,
    page: KClass<P>,
    pageParams: PageParameters? = null,
    markupId: String? = null,
    outputMarkupId: Boolean? = null,
    outputMarkupPlaceholderTag: Boolean? = null,
    visible: Boolean? = null,
    enabled: Boolean? = null,
    visibilityAllowed: Boolean? = null,
    escapeModelStrings: Boolean? = null,
    renderBodyOnly: Boolean? = null,
    behavior: Behavior? = null,
    behaviors: List<Behavior>? = null,
    onConfig: (BookmarkablePageLink<*>.() -> Unit)? = null,
    postInit: (BookmarkablePageLink<*>.() -> Unit)? = null
): BookmarkablePageLink<*> =
    if (onConfig != null) {
        object : BookmarkablePageLink<Any?>(id, page.java, pageParams) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig.invoke(this)
            }

        }
    } else {
        BookmarkablePageLink<Any, P>(id, page.java, pageParams)
    }.config(
        markupId = markupId,
        outputMarkupId = outputMarkupId,
        outputMarkupPlaceholderTag = outputMarkupPlaceholderTag,
        visible = visible,
        enabled = enabled,
        visibilityAllowed = visibilityAllowed,
        escapeModelStrings = escapeModelStrings,
        renderBodyOnly = renderBodyOnly,
        behavior = behavior,
        behaviors = behaviors
    ).also {
        postInit?.invoke(it)
    }