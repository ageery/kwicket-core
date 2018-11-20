package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.StatelessForm
import org.kwicket.component.config
import org.kwicket.component.config.IStatelessFormConfig

/**
 * Creates a [StatelessForm] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [StatelessForm]
 * @param id Wicket component id
 * @param config specifies the settings for the [StatelessForm] component
 * @return [StatelessForm] with the Wicket component id of [id] and configured by [config]
 */
fun <T> statelessFormFactory(id: String, config: IStatelessFormConfig<T>): StatelessForm<T> =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        val onSubmit = config.onSubmit
        val onError = config.onError
        object : StatelessForm<T>(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            override fun onSubmit() {
                onSubmit?.invoke(this)
            }

            override fun onError() {
                onError?.invoke(this)
            }

        }
    } else {
        StatelessForm(id, config.model)
    }.config(config)