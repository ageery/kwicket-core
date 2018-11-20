package org.kwicket.component.factory

import org.apache.wicket.markup.html.media.audio.Audio
import org.kwicket.component.config
import org.kwicket.component.config.IAudioConfig

/**
 * Creates an [Audio] component with the Wicket identifier set to [id] and configured using [config].
 *
 * @param T type of the model of the [Audio]
 * @param id Wicket component id
 * @param config specifies the settings for the [Audio]
 * @return [Audio] with the Wicket component id of [id] and configured by [config]
 */
fun <T> audioFactory(id: String, config: IAudioConfig<T>): Audio =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        if (config.url != null) {
            object : Audio(id, config.model, config.url, config.pageParams) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean =
                    stateless ?: super.getStatelessHint()

            }
        } else {
            object : Audio(id, config.model, config.resRef, config.pageParams) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean =
                    stateless ?: super.getStatelessHint()

            }
        }
    } else {
        if (config.url != null) {
            Audio(id, config.model, config.url, config.pageParams)
        } else {
            Audio(id, config.model, config.resRef, config.pageParams)
        }
    }.config(config)
