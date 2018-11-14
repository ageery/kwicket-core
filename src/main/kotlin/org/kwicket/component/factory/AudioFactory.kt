package org.kwicket.component.factory

import org.apache.wicket.markup.html.media.audio.Audio
import org.kwicket.component.config
import org.kwicket.component.config.IAudioConfig

fun <T> audioFactory(
    id: String,
    config: IAudioConfig<T>
): Audio =
    if (config.isValid) {
        if (config.requiresSubclass) {
            if (config.url != null) {
                object : Audio(id, config.model, config.url, config.pageParams) {
                    override fun onConfigure() {
                        super.onConfigure()
                        config.onConfig?.invoke(this)
                    }
                }
            } else {
                object : Audio(id, config.model, config.resRef, config.pageParams) {
                    override fun onConfigure() {
                        super.onConfigure()
                        config.onConfig?.invoke(this)
                    }
                }
            }
        } else {
            if (config.url != null) {
                Audio(id, config.model, config.url, config.pageParams)
            } else {
                Audio(id, config.model, config.resRef, config.pageParams)
            }
        }.config(config).apply {
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
