package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.basic.MultiLineLabel
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IMultiLineLabelConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates a [MultiLineLabel] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [MultiLineLabel]
 * @param id Wicket component id to use for the [MultiLineLabel]
 * @receiver configuration for creating the [MultiLineLabel]
 * @return [MultiLineLabel] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> IMultiLineLabelConfig<T>.invoke(id: String): MultiLineLabel =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : MultiLineLabel(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        MultiLineLabel(id, model)
    }.config(this)