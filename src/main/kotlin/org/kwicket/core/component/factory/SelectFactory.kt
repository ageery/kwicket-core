package org.kwicket.core.component.factory

import org.apache.wicket.extensions.markup.html.form.select.Select
import org.kwicket.core.component.config
import org.kwicket.core.component.config.ISelectConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates a [Select] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param C type of the [Select]
 * @param T type of the model of the [Select]
 * @param id Wicket component id to use for the [Select]
 * @receiver configuration for creating the [Select]
 * @return [Select] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> ISelectConfig<T>.invoke(id: String): Select<T> {
    val model = model
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : Select<T>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Select<T>(id, model)
    }.config(this)
}