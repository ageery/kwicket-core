package org.kwicket.component.factory

import org.apache.wicket.markup.html.basic.MultiLineLabel
import org.kwicket.component.config
import org.kwicket.component.config.IMultiLineLabelConfig

/**
 * Creates a [MultiLineLabel] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [MultiLineLabel]
 * @param id Wicket component id
 * @param config specifies the settings for the [MultiLineLabel] component
 * @return [MultiLineLabel] with the Wicket component id of [id] and configured by [config]
 */
fun <T> multiLineLabelFactory(id: String, config: IMultiLineLabelConfig<T>): MultiLineLabel =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : MultiLineLabel(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        MultiLineLabel(id, config.model)
    }.config(config)