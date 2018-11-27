package org.kwicket.core.component.factory

import org.apache.wicket.Page
import org.apache.wicket.markup.html.link.BookmarkablePageLink
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IBookmarkablePageLinkConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates an [BookmarkablePageLink] component with the Wicket identifier set to [id] and configured using [config].
 *
 * @param P type of the page
 * @param T type of the model of the [BookmarkablePageLink]
 * @param id Wicket component id
 * @param config specifies the settings for the [BookmarkablePageLink]
 * @return [BookmarkablePageLink] with the Wicket component id of [id] and configured by [config]
 */
operator fun <T, P : Page> IBookmarkablePageLinkConfig<T, P>.invoke(id: String): BookmarkablePageLink<T> {
    val page = this.page?.java
    val pageParams = this.pageParams
    return if (requiresSubclass) {
        val onConfig = this.onConfig
        val stateless = this.stateless
        // FIXME: why does using pageParams as a constructor parameter cause this to be unserializable?
        object : BookmarkablePageLink<T>(id, page, pageParams) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        BookmarkablePageLink<T, P>(id, page, pageParams)
    }.config(this)
}