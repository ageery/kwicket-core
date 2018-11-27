package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.form.Check
import org.kwicket.core.component.config
import org.kwicket.core.component.config.ICheckConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates an [Check] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [Check]
 * @param id Wicket component id to use for the [Check]
 * @receiver configuration for creating the [Check]
 * @return [Check] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> ICheckConfig<T>.invoke(id: String): Check<T> =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : Check<T>(id, model, group) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Check(id, model, group)
    }.config(this)