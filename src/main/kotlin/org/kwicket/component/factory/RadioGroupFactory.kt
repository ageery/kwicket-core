package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.RadioGroup
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.IRadioGroupConfig

/**
 * Creates a [RadioGroup] component with the Wicket identifier set to [id] and configured using [config].

 * @param C type of the [RadioGroup]
 * @param T type of the model of the [RadioGroup]
 * @param id Wicket component id
 * @param config specifies the settings for the [RadioGroup] component
 * @return [RadioGroup] with the Wicket component id of [id] and configured by [config]
 */
fun <C: Any, T: C?> radioGroupFactory(id: String, config: IRadioGroupConfig<C, T>): RadioGroup<C> {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<C?>
    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless

        object : RadioGroup<C>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        RadioGroup<C>(id, model)
    }.config(config)
}