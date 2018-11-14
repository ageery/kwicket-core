package org.kwicket.component.factory

import org.apache.wicket.markup.html.link.StatelessLink
import org.kwicket.component.config
import org.kwicket.component.config.IStatelessLinkConfig

fun <T> statelessLinkFactory(
    id: String,
    config: IStatelessLinkConfig<T>
): StatelessLink<T> =
    object : StatelessLink<T>(id) {

        override fun onConfigure() {
            super.onConfigure()
            config.onConfig?.invoke(this)
        }

        override fun onClick() {
            config.onClick?.invoke(this)
        }

    }.config(config).apply { config.postInit?.invoke(this) }