package org.kwicket.component.factory

import org.apache.wicket.Page
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.kwicket.component.config
import org.kwicket.component.config.IBookmarkablePageLinkConfig

/**
 * Creates an [BookmarkablePageLink] component with the Wicket identifier set to [id] and configured using [config].
 *
 * @param T type of the model of the [BookmarkablePageLink]
 * @param id Wicket component id
 * @param config specifies the settings for the [BookmarkablePageLink]
 * @return [BookmarkablePageLink] with the Wicket component id of [id] and configured by [config]
 */
fun <T, P : Page> bookmarkablePageLinkFactory(
    id: String,
    config: IBookmarkablePageLinkConfig<T, P>
): BookmarkablePageLink<T> {
    val page = config.page?.java
    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : BookmarkablePageLink<T>(id, page, config.pageParams) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        BookmarkablePageLink<T, P>(id, page, config.pageParams)
    }.config(config)
}