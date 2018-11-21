package org.kwicket.component.factory

import org.apache.wicket.markup.html.link.StatelessLink
import org.kwicket.component.config
import org.kwicket.component.config.IStatelessLinkConfig

/**
 * Creates an [StatelessLink] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [StatelessLink]
 * @param id Wicket component id to use for the [StatelessLink]
 * @receiver configuration for creating the [StatelessLink]
 * @return [StatelessLink] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> IStatelessLinkConfig<T>.invoke(id: String): StatelessLink<T> {
    val onConfig = onConfig
    val stateless = stateless
    val onClick = onClick
    return object : StatelessLink<T>(id) {

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