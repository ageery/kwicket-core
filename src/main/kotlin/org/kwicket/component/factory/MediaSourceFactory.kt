package org.kwicket.component.factory

import org.apache.wicket.markup.html.media.Source
import org.kwicket.component.config
import org.kwicket.component.config.IMediaSourceConfig

fun <T> mediaSourceFactory(id: String, config: IMediaSourceConfig<T>): Source =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        if (config.url != null) {
            object : Source(id, config.model, config.url) {

                override fun onConfigure() {
                    super.onConfigure()
                    onConfig?.invoke(this)
                }

                override fun getStatelessHint(): Boolean =
                    stateless ?: super.getStatelessHint()

            }
        } else {
            object : Source(id, config.model, config.resRef) {

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
            Source(id, config.model, config.url)
        } else {
            Source(id, config.model, config.resRef)
        }
    }.config(config).apply {
        config.pageParams?.let { pageParameters = it }
        config.isDisplayType?.let { displayType = it }
        config.type?.let { type = it }
        config.media?.let { type = it }
        config.type?.let { type = it }
    }