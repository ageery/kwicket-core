package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.image.Picture
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IPictureConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates a [Picture] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [Picture]
 * @param id Wicket component id to use for the [Picture]
 * @receiver configuration for creating the [Picture]
 * @return [Picture] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> IPictureConfig<T>.invoke(id: String): Picture =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        val model = model
        object : Picture(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Picture(id, model)
    }.config(this)