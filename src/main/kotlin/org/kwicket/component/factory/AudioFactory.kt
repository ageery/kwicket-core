package org.kwicket.component.factory

import org.apache.wicket.markup.html.media.audio.Audio
import org.kwicket.component.config
import org.kwicket.component.config.IAudioConfig

fun <T> audioFactory(
    id: String,
    config: IAudioConfig<T>
): Audio {
    val onConfig = config.onConfig
    val stateless = config.stateless
    val model = config.model
    val url = config.url
    val pageParams = config.pageParams
    val resRef = config.resRef
    return if (config.requiresSubclass) {

        return if (url != null) {
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
    }.config(config)

}
