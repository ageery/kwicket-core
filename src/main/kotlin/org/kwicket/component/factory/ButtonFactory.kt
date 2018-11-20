package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.Button
import org.kwicket.component.config
import org.kwicket.component.config.IButtonConfig

/**
 * Creates an [Button] component with the Wicket identifier set to [id] and configured using [config].
 *
 * @param id Wicket component id
 * @param config specifies the settings for the [Button]
 * @return [Button] with the Wicket component id of [id] and configured by [config]
 */
fun buttonFactory(id: String, config: IButtonConfig): Button =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        val onSubmit = config.onSubmit
        val onError = config.onError
        object : Button(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            override fun onSubmit() {
                super.onSubmit()
                onSubmit?.invoke(this)
            }

            override fun onError() {
                super.onError()
                onError?.invoke(this)
            }

        }
    } else {
        Button(id, config.model)
    }.config(config)