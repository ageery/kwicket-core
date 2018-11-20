package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.SubmitLink
import org.kwicket.component.config
import org.kwicket.component.config.ISubmitLinkConfig

fun <T> submitLinkFactory(id: String, config: ISubmitLinkConfig<T>): SubmitLink =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : SubmitLink(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        SubmitLink(id, config.model)
    }.config(config)