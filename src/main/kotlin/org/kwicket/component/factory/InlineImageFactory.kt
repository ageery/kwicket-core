package org.kwicket.component.factory

import org.apache.wicket.markup.html.image.InlineImage
import org.kwicket.component.config
import org.kwicket.component.config.IInlineImageConfig

fun <T> inlineImageFactory(
    id: String,
    config: IInlineImageConfig<T>
): InlineImage =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : InlineImage(id, config.model, config.resRef) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        InlineImage(id, config.model, config.resRef)
    }.config(config)