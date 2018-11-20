package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.select.Select
import org.apache.wicket.markup.html.form.RadioGroup
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ISelectConfig

/**
 * Creates a [Select] component with the Wicket identifier set to [id] and configured using [config].

 * @param C type of the [Select]
 * @param T type of the model of the [Select]
 * @param id Wicket component id
 * @param config specifies the settings for the [Select] component
 * @return [Select] with the Wicket component id of [id] and configured by [config]
 */
fun <C: Any, T: C?> selectFactory(id: String, config: ISelectConfig<C, T>): Select<C> {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<C?>
    return if (config.requiresSubclass) {

        object : Select<C>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                config.onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = config.stateless ?: super.getStatelessHint()

        }
    } else {
        Select<C>(id, model)
    }.config(config)
}