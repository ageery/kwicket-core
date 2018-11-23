package org.kwicket.component.factory

import org.apache.wicket.devutils.debugbar.DebugBar
import org.kwicket.component.config
import org.kwicket.component.config.IDebugBarConfig
import org.kwicket.component.config.requiresSubclass

/**
 * Creates an [DebugBar] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param id Wicket component id to use for the [DebugBar]
 * @receiver configuration for creating the [DebugBar]
 * @return [DebugBar] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun IDebugBarConfig.invoke(id: String): DebugBar {
    var isInitiallyExpanded = isInitiallyExpanded ?: true
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : DebugBar(id, isInitiallyExpanded) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        DebugBar(id, isInitiallyExpanded)
    }.config(this)
}