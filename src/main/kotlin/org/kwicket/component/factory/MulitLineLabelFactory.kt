package org.kwicket.component.factory

import org.apache.wicket.markup.html.basic.MultiLineLabel
import org.kwicket.component.config
import org.kwicket.component.config.IMultiLineLabelConfig

fun <T> multiLineLabelFactory(id: String, config: IMultiLineLabelConfig<T>): MultiLineLabel =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : MultiLineLabel(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        MultiLineLabel(id, config.model)
    }.config(config)