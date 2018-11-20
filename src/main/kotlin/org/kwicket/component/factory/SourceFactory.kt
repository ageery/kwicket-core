package org.kwicket.component.factory

import org.apache.wicket.markup.html.image.Source
import org.kwicket.component.config
import org.kwicket.component.config.ISourceConfig

/**
 * Creates a [Source] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [Source]
 * @param id Wicket component id
 * @param config specifies the settings for the [Source] component
 * @return [Source] with the Wicket component id of [id] and configured by [config]
 */
fun <T> sourceFactory(id: String, config: ISourceConfig<T>): Source =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless

        object : Source(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Source(id, config.model)
    }.config(config)
        .also { source ->
            config.media?.let { source.media = it }
            config.crossOrigin?.let { source.crossOrigin = it }
        }