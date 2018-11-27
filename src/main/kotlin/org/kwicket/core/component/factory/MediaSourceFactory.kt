package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.media.Source
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IMediaSourceConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates a [Source] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [Source]
 * @param id Wicket component id to use for the [Source]
 * @receiver configuration for creating the [Source]
 * @return [Source] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> IMediaSourceConfig<T>.invoke(id: String): Source =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        val model = model
        if (url != null) {
            val url = url
            object : Source(id, model, url) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        } else {
            val resRef = resRef
            object : Source(id, model, resRef) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            }
        }
    } else {
        if (url != null) {
            Source(id, model, url)
        } else {
            Source(id, model, resRef)
        }
    }.config(this)
        .apply {
            pageParams?.let { pageParameters = it }
            isDisplayType?.let { displayType = it }
            type?.let { type = it }
            media?.let { type = it }
            type?.let { type = it }
        }