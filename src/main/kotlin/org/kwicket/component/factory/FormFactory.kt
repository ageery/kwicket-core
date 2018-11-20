package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.Form
import org.kwicket.component.config
import org.kwicket.component.config.IFormConfig

/**
 * Creates a [Form] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [Form]
 * @param id Wicket component id
 * @param config specifies the settings for the [Form] component
 * @return [Form] with the Wicket component id of [id] and configured by [config]
 */
fun <T> formFactory(id: String, config: IFormConfig<T>): Form<T> =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        val onSubmit = config.onSubmit
        val onError = config.onError
        object : Form<T>(id, config.model) {

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
        Form<T>(id, config.model)
    }.config(config)