package org.kwicket.component.factory

import org.apache.wicket.markup.html.link.ExternalLink
import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.kwicket.component.config
import org.kwicket.component.config.IFeedbackPanelConfig

/**
 * Creates an [FeedbackPanel] component with the Wicket identifier set to [id] and configured using [config].

 * @param id Wicket component id
 * @param config specifies the settings for the [FeedbackPanel] component
 * @return [FeedbackPanel] with the Wicket component id of [id] and configured by [config]
 */
fun feedbackPanelFactory(id: String, config: IFeedbackPanelConfig): FeedbackPanel =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : FeedbackPanel(id, config.filter) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        FeedbackPanel(id, config.filter)
    }.config(config)