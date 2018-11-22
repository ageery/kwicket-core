package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.RadioGroup
import org.kwicket.component.config
import org.kwicket.component.config.IRadioGroupConfig

/**
 * Creates a [RadioGroup] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param C type of the [RadioGroup]
 * @param T type of the model of the [RadioGroup]
 * @param id Wicket component id to use for the [RadioGroup]
 * @receiver configuration for creating the [RadioGroup]
 * @return [RadioGroup] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> IRadioGroupConfig<T>.invoke(id: String): RadioGroup<T> {
    val model = model
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless

        object : RadioGroup<T>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        RadioGroup<T>(id, model)
    }.config(this)
}