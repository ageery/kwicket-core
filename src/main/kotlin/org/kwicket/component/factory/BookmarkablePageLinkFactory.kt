package org.kwicket.component.factory

import org.apache.wicket.Page
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.kwicket.component.config
import org.kwicket.component.config.IBookmarkablePageLinkConfig

fun <T, P: Page> bookmarkablePageLinkFactory(
    id: String,
    config: IBookmarkablePageLinkConfig<T, P>
): BookmarkablePageLink<T> {
    val onConfig = config.onConfig
    val stateless = config.stateless
    val pageParams = config.pageParams
    val page = config.page?.java
    return if (config.requiresSubclass) {
        object : BookmarkablePageLink<T>(id, page, pageParams) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        BookmarkablePageLink<T, P>(id, page, pageParams)
    }.config(config)
}