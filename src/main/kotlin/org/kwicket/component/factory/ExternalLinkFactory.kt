package org.kwicket.component.factory

import org.apache.wicket.markup.html.link.ExternalLink
import org.kwicket.component.config
import org.kwicket.component.config.IExternalLinkConfig

/**
 * Creates an [ExternalLink] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param id Wicket component id to use for the [ExternalLink]
 * @receiver configuration for creating the [ExternalLink]
 * @return [ExternalLink] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun IExternalLinkConfig.invoke(id: String): ExternalLink =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : ExternalLink(id, model, label) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        ExternalLink(id, model, label)
    }.config(this)