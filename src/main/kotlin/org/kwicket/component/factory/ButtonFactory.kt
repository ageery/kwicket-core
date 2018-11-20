package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.Button
import org.kwicket.component.config
import org.kwicket.component.config.IButtonConfig

fun buttonFactory(
    id: String,
    config: IButtonConfig
): Button {
    val onConfig = config.onConfig
    val model = config.model
    val stateless = config.stateless
    val onSubmit = config.onSubmit
    val onError = config.onError
    return if (config.requiresSubclass) {
        object : Button(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

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
    }.config(config)
}