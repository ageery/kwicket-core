package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.RadioGroup
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.IRadioGroupConfig

fun <C: Any, T: C?> radioGroupFactory(id: String, config: IRadioGroupConfig<C, T>): RadioGroup<C> {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<C>
    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless

        object : RadioGroup<C>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        RadioGroup(id, model)
    }.config(config)
}