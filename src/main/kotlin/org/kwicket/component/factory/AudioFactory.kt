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
operator fun <T> IAudioConfig<T>.invoke(id: String): Audio =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        if (url != null) {
            object : Audio(id, model, url, pageParams) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean =
                    stateless ?: super.getStatelessHint()

            }
        } else {
            object : Audio(id, model, resRef, pageParams) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean =
                    stateless ?: super.getStatelessHint()

            }
        }
    } else {
        if (url != null) {
            Audio(id, model, url, pageParams)
        } else {
            Audio(id, model, resRef, pageParams)
        }
    }.config(this)
