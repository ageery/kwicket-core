package org.kwicket.component.factory

import org.apache.wicket.markup.html.image.InlineImage
import org.kwicket.component.config
import org.kwicket.component.config.IInlineImageConfig

/**
 * Creates a [InlineImage] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [InlineImage]
 * @param id Wicket component id
 * @param config specifies the settings for the [InlineImage] component
 * @return [InlineImage] with the Wicket component id of [id] and configured by [config]
 */
fun <T> inlineImageFactory(id: String, config: IInlineImageConfig<T>): InlineImage =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : InlineImage(id, config.model, config.resRef) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        InlineImage(id, config.model, config.resRef)
    }.config(config)