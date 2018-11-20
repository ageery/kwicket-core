package org.kwicket.component.factory

import org.apache.wicket.markup.html.basic.Label
import org.kwicket.component.config
import org.kwicket.component.config.ILabelConfig

fun <T> labelFactory(id: String, config: ILabelConfig<T>): Label =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : Label(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        Label(id, config.model)
    }.config(config)