package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.Form
import org.kwicket.component.config
import org.kwicket.component.config.IFormConfig

fun <T> formFactory(
    id: String,
    config: IFormConfig<T>
): Form<T> =
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

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

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