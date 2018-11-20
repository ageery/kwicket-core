package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.CheckBox
import org.kwicket.component.config
import org.kwicket.component.config.ICheckBoxConfig

/**
 * Creates an [CheckBox] component with the Wicket identifier set to [id] and configured using [config].
 *
 * @param id Wicket component id
 * @param config specifies the settings for the [CheckBox]
 * @return [CheckBox] with the Wicket component id of [id] and configured by [config]
 */
fun checkBoxFactory(id: String, config: ICheckBoxConfig): CheckBox =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : CheckBox(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        CheckBox(id, config.model)
    }.config(config)