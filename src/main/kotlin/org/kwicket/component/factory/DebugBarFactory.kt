package org.kwicket.component.factory

import org.apache.wicket.devutils.debugbar.DebugBar
import org.kwicket.component.config
import org.kwicket.component.config.IDebugBarConfig

fun debugBarFactory(
    id: String,
    config: IDebugBarConfig
): DebugBar {
    var isInitiallyExpanded = config.isInitiallyExpanded ?: true
    val onConfig = config.onConfig
    val stateless = config.stateless
    return if (config.requiresSubclass) {
        object : DebugBar(id, isInitiallyExpanded) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        DebugBar(id, isInitiallyExpanded)
    }.config(config)
}