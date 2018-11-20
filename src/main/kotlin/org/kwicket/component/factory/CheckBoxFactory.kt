package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.CheckBox
import org.kwicket.component.config
import org.kwicket.component.config.ICheckBoxConfig

fun checkBoxFactory(
    id: String,
    config: ICheckBoxConfig
): CheckBox {
    val model = config.model
    val onConfig = config.onConfig
    val stateless = config.stateless
    return if (config.requiresSubclass) {
        object : CheckBox(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        CheckBox(id, model)
    }.config(config)
}