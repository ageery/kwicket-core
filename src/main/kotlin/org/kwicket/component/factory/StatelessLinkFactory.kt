package org.kwicket.component.factory

import org.apache.wicket.markup.html.link.StatelessLink
import org.kwicket.component.config
import org.kwicket.component.config.IStatelessLinkConfig

fun <T> statelessLinkFactory(id: String, config: IStatelessLinkConfig<T>): StatelessLink<T> {
    val onConfig = config.onConfig
    val stateless = config.stateless
    val onClick = config.onClick
    return object : StatelessLink<T>(id) {

        override fun onConfigure() {
            super.onConfigure()
            onConfig?.invoke(this)
        }

        override fun getStatelessHint(): Boolean =
            stateless ?: super.getStatelessHint()

        override fun onClick() {
            onClick?.invoke(this)
        }

    }.config(config)
}