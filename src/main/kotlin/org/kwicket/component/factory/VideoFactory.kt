package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.SubmitLink
import org.apache.wicket.markup.html.media.video.Video
import org.kwicket.component.config
import org.kwicket.component.config.IVideoConfig

/**
 * Creates a [Video] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [Video]
 * @param id Wicket component id
 * @param config specifies the settings for the [Video] component
 * @return [Video] with the Wicket component id of [id] and configured by [config]
 */
fun <T> videoFactory(id: String, config: IVideoConfig<T>): Video =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        if (config.url != null) {
            object : Video(id, config.model, config.url, config.pageParams) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        } else {
            object : Video(id, config.model, config.resRef, config.pageParams) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        }
    } else {
        if (config.url != null) {
            Video(id, config.model, config.url, config.pageParams)
        } else {
            Video(id, config.model, config.resRef, config.pageParams)
        }
    }.config(config)
        .apply {
        config.width?.let { width = it }
        config.height?.let { height = it }
        config.poster?.let { poster = it }
        config.isMuted?.let { isMuted = it }
        config.hasControls?.let { setControls(it) }
        config.preload?.let { preload = it }
        config.isAutoPlay?.let { isAutoplay = it }
        config.isLooping?.let { isLooping = it }
        config.startTime?.let { startTime = it }
        config.endTime?.let { endTime = it }
        config.mediaGroup?.let { mediaGroup = it }
        config.crossOrigin?.let { crossOrigin = it }
        config.type?.let { type = it }
    }
