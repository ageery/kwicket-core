package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.link.Link
import org.kwicket.core.component.config
import org.kwicket.core.component.config.ILinkConfig

// FIXME: should stateless also override getURL()?

/**
 * Creates an [Link] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [Link]
 * @param id Wicket component id to use for the [Link]
 * @receiver configuration for creating the [Link]
 * @return [Link] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> ILinkConfig<T>.invoke(id: String): Link<T> {
    val onConfig = onConfig
    val stateless = stateless
    val onClick = onClick
    return object : Link<T>(id, model) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        override fun onClick() {
            onClick?.invoke(this)
        }

    }.config(this)
}