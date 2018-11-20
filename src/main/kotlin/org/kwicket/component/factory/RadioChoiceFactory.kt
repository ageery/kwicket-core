package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.RadioChoice
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.IRadioChoiceConfig

fun <C: Any, T: C?> radioChoiceFactory(id: String, config: IRadioChoiceConfig<C, T>): RadioChoice<C> {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<C?>
    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : RadioChoice<C>(id, model, config.choices, config.choiceRenderer) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        RadioChoice<C>(id, model, config.choices, config.choiceRenderer)
    }.config(config)
}