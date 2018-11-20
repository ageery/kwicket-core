package org.kwicket.component.factory

import org.apache.wicket.markup.html.image.Image
import org.kwicket.component.config
import org.kwicket.component.config.IImageConfig

fun <T> imageFactory(id: String, config: IImageConfig<T>): Image =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : Image(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        Image(id, config.model)
    }.config(config)
