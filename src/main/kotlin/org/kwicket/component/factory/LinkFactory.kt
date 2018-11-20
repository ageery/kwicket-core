package org.kwicket.component.factory

import org.apache.wicket.markup.html.link.Link
import org.kwicket.component.config
import org.kwicket.component.config.ILinkConfig

/**
 * Creates a [Link] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [Link]
 * @param id Wicket component id
 * @param config specifies the settings for the [Link] component
 * @return [Link] with the Wicket component id of [id] and configured by [config]
 */
fun <T> linkFactory(id: String, config: ILinkConfig<T>): Link<T> {
    val onConfig = config.onConfig
    val stateless = config.stateless
    val onClick = config.onClick
    return object : Link<T>(id, config.model) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        override fun onClick() {
            onClick?.invoke(this)
        }

    }.config(config)
}