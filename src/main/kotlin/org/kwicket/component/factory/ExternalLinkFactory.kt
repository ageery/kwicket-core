package org.kwicket.component.factory

import org.apache.wicket.markup.html.link.ExternalLink
import org.kwicket.component.config
import org.kwicket.component.config.IExternalLinkConfig

fun externalLinkFactory(
    id: String,
    config: IExternalLinkConfig
): ExternalLink {
    val onConfig = config.onConfig
    val stateless = config.stateless
    val label = config.label
    val model = config.model
    return if (config.requiresSubclass) {
        object : ExternalLink(id, model, label) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        ExternalLink(id, model, label)
    }.config(config)
}