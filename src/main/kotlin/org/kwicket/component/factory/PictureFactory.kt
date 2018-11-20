package org.kwicket.component.factory

import org.apache.wicket.markup.html.image.Picture
import org.kwicket.component.config
import org.kwicket.component.config.IPictureConfig

/**
 * Creates a [Picture] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [Picture]
 * @param id Wicket component id
 * @param config specifies the settings for the [Picture] component
 * @return [Picture] with the Wicket component id of [id] and configured by [config]
 */
fun <T> pictureFactory(id: String, config: IPictureConfig<T>): Picture =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : Picture(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Picture(id, config.model)
    }.config(config)