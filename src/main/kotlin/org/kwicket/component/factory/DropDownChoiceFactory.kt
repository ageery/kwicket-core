package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.IDropDownChoiceConfig

fun <C: Any, T: C?> dropDownChoiceFactory(
    id: String,
    config: IDropDownChoiceConfig<C, T>
): DropDownChoice<C> {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<C?>
    val onConfig = config.onConfig
    val stateless = config.stateless
    val choices = config.choices
    val choiceRenderer = config.choiceRenderer
    return if (config.requiresSubclass) {
        object : DropDownChoice<C>(id, model, choices, choiceRenderer) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }

    } else {
        DropDownChoice<C>(id, model, choices, choiceRenderer)
    }.config(config)
}