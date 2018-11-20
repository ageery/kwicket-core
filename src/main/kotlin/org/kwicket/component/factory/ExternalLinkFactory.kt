package org.kwicket.component.factory

import org.apache.wicket.markup.html.link.ExternalLink
import org.kwicket.component.config
import org.kwicket.component.config.IExternalLinkConfig

/**
 * Creates an [ExternalLink] component with the Wicket identifier set to [id] and configured using [config].

 * @param id Wicket component id
 * @param config specifies the settings for the [ExternalLink] component
 * @return [ExternalLink] with the Wicket component id of [id] and configured by [config]
 */
fun externalLinkFactory(id: String, config: IExternalLinkConfig): ExternalLink =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : ExternalLink(id, config.model, config.label) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        ExternalLink(id, config.model, config.label)
    }.config(config)