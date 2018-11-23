package org.kwicket.component.factory

import org.apache.wicket.markup.html.image.InlineImage
import org.kwicket.component.config
import org.kwicket.component.config.IInlineImageConfig
import org.kwicket.component.config.requiresSubclass

/**
 * Creates an [InlineImage] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [InlineImage]
 * @param id Wicket component id to use for the [InlineImage]
 * @receiver configuration for creating the [InlineImage]
 * @return [InlineImage] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> IInlineImageConfig<T>.invoke(id: String): InlineImage =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : InlineImage(id, model, resRef) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        InlineImage(id, model, resRef)
    }.config(this)