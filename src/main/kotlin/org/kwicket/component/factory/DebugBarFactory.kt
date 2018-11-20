package org.kwicket.component.factory

import org.apache.wicket.devutils.debugbar.DebugBar
import org.kwicket.component.config
import org.kwicket.component.config.IDebugBarConfig

/**
 * Creates an [DebugBar] component with the Wicket identifier set to [id] and configured using [config].
 *
 * @param id Wicket component id
 * @param config specifies the settings for the [DebugBar]
 * @return [DebugBar] with the Wicket component id of [id] and configured by [config]
 */
fun debugBarFactory(id: String, config: IDebugBarConfig): DebugBar {
    var isInitiallyExpanded = config.isInitiallyExpanded ?: true
    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : DebugBar(id, isInitiallyExpanded) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        DebugBar(id, isInitiallyExpanded)
    }.config(config)
}