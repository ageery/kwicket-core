package org.kwicket.component.factory

import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.kwicket.component.config
import org.kwicket.component.config.IFeedbackPanelConfig
import org.kwicket.component.config.requiresSubclass

/**
 * Creates an [FeedbackPanel] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param id Wicket component id to use for the [FeedbackPanel]
 * @receiver configuration for creating the [FeedbackPanel]
 * @return [FeedbackPanel] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun IFeedbackPanelConfig.invoke(id: String): FeedbackPanel =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : FeedbackPanel(id, filter) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        FeedbackPanel(id, filter)
    }.config(this)