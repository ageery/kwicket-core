package org.kwicket.component.factory

import org.apache.wicket.markup.html.image.Source
import org.kwicket.component.config
import org.kwicket.component.config.ISourceConfig

fun <T> sourceFactory(id: String, config: ISourceConfig<T>): Source =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless

        object : Source(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        Source(id, config.model)
    }.config(config)
        .also { source ->
            config.media?.let { source.media = it }
            config.crossOrigin?.let { source.crossOrigin = it }
        }