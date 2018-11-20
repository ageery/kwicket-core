package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.DropDownChoice
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.IDropDownChoiceConfig

// .resM() -- resource model, .strM() -- string resource model, litM() -- literal model
// val myLabel: Label = LabelConfig(model = "test".m()) + "myLabel"
// val myLabel2: Label = "myLabel" + LabelConfig(model = "test".m())
// val myLabel3: Label = LabelConfig(model = "test".m(), outputMarkupId = true)("myid3") ** winner, winner

/**
 * Creates a [DropDownChoice] component with the Wicket identifier set to [id] and configured using [config].

 * @param C type of the [DropDownChoice]
 * @param T type of the model of the [DropDownChoice]
 * @param id Wicket component id
 * @param config specifies the settings for the [DropDownChoice] component
 * @return [DropDownChoice] with the Wicket component id of [id] and configured by [config]
 */
fun <C: Any, T: C?> dropDownChoiceFactory(id: String, config: IDropDownChoiceConfig<C, T> ): DropDownChoice<C> {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<C?>
    val choices = config.choices
    val choiceRenderer = config.choiceRenderer
    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : DropDownChoice<C>(id, model, choices, choiceRenderer) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }

    } else {
        DropDownChoice<C>(id, model, choices, choiceRenderer)
    }.config(config)
}