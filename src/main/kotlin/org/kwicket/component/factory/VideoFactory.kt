package org.kwicket.component.factory

import org.apache.wicket.markup.html.media.video.Video
import org.kwicket.component.config
import org.kwicket.component.config.IVideoConfig

fun <T> videoFactory(
    id: String,
    config: IVideoConfig<T>
): Video =
    if (config.isValid) {
        if (config.requiresSubclass) {
            if (config.url != null) {
                object : Video(id, config.model, config.url, config.pageParams) {
                    override fun onConfigure() {
                        super.onConfigure()
                        config.onConfig?.invoke(this)
                    }
                }
            } else {
                object : Video(id, config.model, config.resRef, config.pageParams) {
                    override fun onConfigure() {
                        super.onConfigure()
                        config.onConfig?.invoke(this)
                    }
                }
            }
        } else {
            if (config.url != null) {
                Video(id, config.model, config.url, config.pageParams)
            } else {
                Video(id, config.model, config.resRef, config.pageParams)
            }
        }.config(config).apply {
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
            config.postInit?.invoke(this)
        }
    } else {
        throw RuntimeException("Config is not valid")
    }
