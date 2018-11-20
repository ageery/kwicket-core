package org.kwicket.component.factory

import org.apache.wicket.markup.html.WebMarkupContainer
import org.kwicket.component.config
import org.kwicket.component.config.IWebMarkupContainerConfig

/**
 * Creates a [WebMarkupContainer] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [WebMarkupContainer]
 * @param id Wicket component id
 * @param config specifies the settings for the [WebMarkupContainer] component
 * @return [WebMarkupContainer] with the Wicket component id of [id] and configured by [config]
 */
fun <T> webMarkupContainerFactory(id: String, config: IWebMarkupContainerConfig<T>): WebMarkupContainer =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : WebMarkupContainer(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        WebMarkupContainer(id, config.model)
    }.config(config)