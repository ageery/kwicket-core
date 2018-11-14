package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.StatelessForm
import org.kwicket.component.config
import org.kwicket.component.config.IStatelessFormConfig

fun <T> statelessFormFactory(
    id: String,
    config: IStatelessFormConfig<T>
): StatelessForm<T> =
    if (config.requiresSubclass) {
        object : StatelessForm<T>(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                config.onConfig?.invoke(this)
            }

            override fun onSubmit() {
                config.onSubmit?.invoke(this)
            }

            override fun onError() {
                config.onError?.invoke(this)
            }

        }
    } else {
        StatelessForm(id, config.model)
    }.config(config).apply { config.postInit?.invoke(this) }