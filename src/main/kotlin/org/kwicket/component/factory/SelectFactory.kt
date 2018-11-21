package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.select.Select
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ISelectConfig

/**
 * Creates a [Select] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param C type of the [Select]
 * @param T type of the model of the [Select]
 * @param id Wicket component id to use for the [Select]
 * @receiver configuration for creating the [Select]
 * @return [Select] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <C: Any, T: C?> ISelectConfig<C, T>.invoke(id: String): Select<C> {
    @Suppress("UNCHECKED_CAST")
    val model = model as IModel<C?>
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : Select<C>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Select<C>(id, model)
    }.config(this)
}