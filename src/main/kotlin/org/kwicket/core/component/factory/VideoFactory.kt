package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.media.video.Video
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IVideoConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates a [Video] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [Video]
 * @param id Wicket component id
 * @param config specifies the settings for the [Video] component
 * @return [Video] with the Wicket component id of [id] and configured by [config]
 */
operator fun <T> IVideoConfig<T>.invoke(id: String): Video =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        val model = model
        val pageParams = pageParams
        if (url != null) {
            val url = url
            object : Video(id, model, url, pageParams) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        } else {
            val resRef = resRef
            object : Video(id, model, resRef, pageParams) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        }
    } else {
        if (url != null) {
            Video(id, model, url, pageParams)
        } else {
            Video(id, model, resRef, pageParams)
        }
    }.config(this)
        .apply {
            this@invoke.width?.let { width = it }
            this@invoke.height?.let { height = it }
            this@invoke.poster?.let { poster = it }
            this@invoke.isMuted?.let { isMuted = it }
            this@invoke.hasControls?.let { setControls(it) }
            this@invoke.preload?.let { preload = it }
            this@invoke.isAutoPlay?.let { isAutoplay = it }
            this@invoke.isLooping?.let { isLooping = it }
            this@invoke.startTime?.let { startTime = it }
            this@invoke.endTime?.let { endTime = it }
            this@invoke.mediaGroup?.let { mediaGroup = it }
            this@invoke.crossOrigin?.let { crossOrigin = it }
            this@invoke.type?.let { type = it }
        }
