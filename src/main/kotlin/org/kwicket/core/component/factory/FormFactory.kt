package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.form.Form
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IFormConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates an [Form] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [Form]
 * @param id Wicket component id to use for the [Form]
 * @receiver configuration for creating the [Form]
 * @return [Form] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> IFormConfig<T>.invoke(id: String): Form<T> =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless =stateless
        val onSubmit = onSubmit
        val onError = onError
        object : Form<T>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            override fun onSubmit() {
                super.onConfigure()
                onSubmit?.invoke(this)
            }

            override fun onError() {
                super.onConfigure()
                onError?.invoke(this)
            }

        }
    } else {
        Form<T>(id, model)
    }.config(this)