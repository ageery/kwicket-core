package org.kwicket.component.factory

import org.apache.wicket.markup.html.WebMarkupContainer
import org.kwicket.component.config
import org.kwicket.component.config.IWebMarkupContainerConfig

fun <T> webMarkupContainerFactory(id: String, config: IWebMarkupContainerConfig<T>): WebMarkupContainer =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : WebMarkupContainer(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        WebMarkupContainer(id, config.model)
    }.config(config)