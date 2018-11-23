package org.kwicket.component.factory

import org.apache.wicket.markup.html.basic.Label
import org.kwicket.component.config
import org.kwicket.component.config.ILabelConfig
import org.kwicket.component.config.requiresSubclass

/**
 * Creates an [Label] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [Label]
 * @param id Wicket component id to use for the [Label]
 * @receiver configuration for creating the [Label]
 * @return [Label] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> ILabelConfig<T>.invoke(id: String): Label =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : Label(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Label(id, model)
    }.config(this)