package org.kwicket.component.factory

import org.apache.wicket.markup.html.panel.FeedbackPanel
import org.kwicket.component.config
import org.kwicket.component.config.IFeedbackPanelConfig

fun feedbackPanelFactory(
    id: String,
    config: IFeedbackPanelConfig
): FeedbackPanel {
    val onConfig = config.onConfig
    val stateless = config.stateless
    val filter = config.filter
    return if (config.requiresSubclass) {
        object : FeedbackPanel(id, filter) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        FeedbackPanel(id, filter)
    }.config(config)
}