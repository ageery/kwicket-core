package org.kwicket.core.component.factory

import org.apache.wicket.ajax.markup.html.form.AjaxButton
import org.apache.wicket.markup.html.form.Button
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IButtonConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates an [Button] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param id Wicket component id to use for the [Button]
 * @receiver configuration for creating the [Button]
 * @return [Button] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun IButtonConfig.invoke(id: String): Button =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        val onSubmit = onSubmit
        val onError = onError
        object : Button(id, model) {

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
        Button(id, model)
    }.config(this)