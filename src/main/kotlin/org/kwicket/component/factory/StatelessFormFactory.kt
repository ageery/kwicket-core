package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.StatelessForm
import org.kwicket.component.config
import org.kwicket.component.config.IStatelessFormConfig

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

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

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