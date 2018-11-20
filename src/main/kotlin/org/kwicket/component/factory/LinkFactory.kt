package org.kwicket.component.factory

import org.apache.wicket.markup.html.link.Link
import org.kwicket.component.config
import org.kwicket.component.config.ILinkConfig

fun <T> linkFactory(id: String, config: ILinkConfig<T>): Link<T> {
    val onConfig = config.onConfig
    val stateless = config.stateless
    val onClick = config.onClick
    return object : Link<T>(id, config.model) {

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