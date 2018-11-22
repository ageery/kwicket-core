package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.RadioChoice
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.IRadioChoiceConfig

/**
 * Creates a [RadioChoice] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param C type of the [RadioChoice]
 * @param T type of the model of the [RadioChoice]
 * @param id Wicket component id to use for the [RadioChoice]
 * @receiver configuration for creating the [RadioChoice]
 * @return [RadioChoice] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> IRadioChoiceConfig<T>.invoke(id: String): RadioChoice<T> {
    @Suppress("UNCHECKED_CAST")
    val model = model as IModel<T?>
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        val choices = choices
        val choiceRenderer = choiceRenderer
        object : RadioChoice<T>(id, model, choices, choiceRenderer) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        RadioChoice<T>(id, model, choices, choiceRenderer)
    }.config(this)
}