package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.Check
import org.kwicket.component.config
import org.kwicket.component.config.ICheckConfig

fun <T> checkFactory(
    id: String,
    config: ICheckConfig<T>
): Check<T> {
    val model = config.model
    val group = config.group
    val onConfig = config.onConfig
    val stateless = config.stateless
    return if (config.requiresSubclass) {
        object : Check<T>(id, model, group) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        Check(id, model, group)
    }.config(config)
}