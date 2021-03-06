package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.form.TextField
import org.kwicket.core.component.config
import org.kwicket.core.component.config.ITextFieldConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates a [TextField] component with the Wicket identifier set to [id] and configured using [config].

 * @param C type of the [TextField]
 * @param T type of the model of the [TextField]
 * @param id Wicket component id
 * @param config specifies the settings for the [TextField] component
 * @return [TextField] with the Wicket component id of [id] and configured by [config]
 */
operator fun <T> ITextFieldConfig<T>.invoke(id: String): TextField<T> {
    val model = model
    val type = type

    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : TextField<T>(id, model, type) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()
        }
    } else {
        TextField<T>(id, model, type)
    }.config(this)
}