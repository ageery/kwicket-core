package org.kwicket.component.factory

import org.apache.wicket.markup.html.image.Picture
import org.kwicket.component.config
import org.kwicket.component.config.IPictureConfig

fun <T> pictureFactory(id: String, config: IPictureConfig<T>): Picture =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : Picture(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        Picture(id, config.model)
    }.config(config)