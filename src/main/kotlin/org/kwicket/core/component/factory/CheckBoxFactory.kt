package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.form.Button
import org.apache.wicket.markup.html.form.CheckBox
import org.kwicket.core.component.config
import org.kwicket.core.component.config.ICheckBoxConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates an [CheckBox] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param id Wicket component id to use for the [CheckBox]
 * @receiver configuration for creating the [CheckBox]
 * @return [CheckBox] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun ICheckBoxConfig.invoke(id: String): CheckBox =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : CheckBox(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        CheckBox(id, model)
    }.config()