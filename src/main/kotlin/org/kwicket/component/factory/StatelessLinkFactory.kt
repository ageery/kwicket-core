package org.kwicket.component.factory

import org.apache.wicket.markup.html.link.StatelessLink
import org.kwicket.component.config
import org.kwicket.component.config.IStatelessLinkConfig

/**
 * Creates a [StatelessLink] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [StatelessLink]
 * @param id Wicket component id
 * @param config specifies the settings for the [StatelessLink] component
 * @return [StatelessLink] with the Wicket component id of [id] and configured by [config]
 */
fun <T> statelessLinkFactory(id: String, config: IStatelessLinkConfig<T>): StatelessLink<T> {
    val onConfig = config.onConfig
    val stateless = config.stateless
    val onClick = config.onClick
    return object : StatelessLink<T>(id) {

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