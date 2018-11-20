package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.Check
import org.kwicket.component.config
import org.kwicket.component.config.ICheckConfig

/**
 * Creates an [Check] component with the Wicket identifier set to [id] and configured using [config].
 *
 * @param T type of the model of the [Check]
 * @param id Wicket component id
 * @param config specifies the settings for the [Check]
 * @return [Check] with the Wicket component id of [id] and configured by [config]
 */
fun <T> checkFactory(id: String, config: ICheckConfig<T>): Check<T> =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : Check<T>(id, config.model, config.group) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Check(id, config.model, config.group)
    }.config(config)