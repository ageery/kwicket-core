package org.kwicket.component.factory

import org.apache.wicket.markup.html.basic.Label
import org.apache.wicket.markup.html.image.InlineImage
import org.kwicket.component.config
import org.kwicket.component.config.ILabelConfig

/**
 * Creates a [Label] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [Label]
 * @param id Wicket component id
 * @param config specifies the settings for the [Label] component
 * @return [Label] with the Wicket component id of [id] and configured by [config]
 */
fun <T> labelFactory(id: String, config: ILabelConfig<T>): Label =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : Label(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Label(id, config.model)
    }.config(config)