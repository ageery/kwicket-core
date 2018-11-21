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
 * Creates an [DropDownChoice] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param C type of the [DropDownChoice]
 * @param T type of the model of the [DropDownChoice]
 * @param id Wicket component id to use for the [DropDownChoice]
 * @receiver configuration for creating the [DropDownChoice]
 * @return [DropDownChoice] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <C: Any, T: C?> IDropDownChoiceConfig<C, T>.invoke(id: String): DropDownChoice<C> {
    @Suppress("UNCHECKED_CAST")
    val model = model as IModel<C?>
    val choices = choices
    val choiceRenderer = choiceRenderer
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : DropDownChoice<C>(id, model, choices, choiceRenderer) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }

    } else {
        DropDownChoice<C>(id, model, choices, choiceRenderer)
    }.config(this)
}