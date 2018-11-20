package org.kwicket.component.factory

import org.apache.wicket.markup.html.image.Image
import org.kwicket.component.config
import org.kwicket.component.config.IImageConfig

/**
 * Creates a [Image] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [Image]
 * @param id Wicket component id
 * @param config specifies the settings for the [Image] component
 * @return [Image] with the Wicket component id of [id] and configured by [config]
 */
fun <T> imageFactory(id: String, config: IImageConfig<T>): Image =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : Image(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Image(id, config.model)
    }.config(config)
