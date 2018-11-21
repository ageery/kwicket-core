package org.kwicket.component.factory

import org.apache.wicket.markup.html.image.Image
import org.kwicket.component.config
import org.kwicket.component.config.IImageConfig

/**
 * Creates an [Image] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [Image]
 * @param id Wicket component id to use for the [Image]
 * @receiver configuration for creating the [Image]
 * @return [Image] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> IImageConfig<T>.invoke(id: String): Image =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : Image(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Image(id, model)
    }.config(this)
