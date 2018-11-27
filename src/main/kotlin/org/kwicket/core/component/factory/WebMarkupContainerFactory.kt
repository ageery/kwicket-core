package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.WebMarkupContainer
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IWebMarkupContainerConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates a [WebMarkupContainer] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [WebMarkupContainer]
 * @param id Wicket component id
 * @param config specifies the settings for the [WebMarkupContainer] component
 * @return [WebMarkupContainer] with the Wicket component id of [id] and configured by [config]
 */
operator fun <T> IWebMarkupContainerConfig<T>.invoke(id: String): WebMarkupContainer =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : WebMarkupContainer(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        WebMarkupContainer(id, model)
    }.config(this)