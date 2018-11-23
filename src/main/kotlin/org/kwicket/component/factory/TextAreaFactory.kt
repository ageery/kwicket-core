package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.TextArea
import org.kwicket.component.config
import org.kwicket.component.config.ITextAreaConfig
import org.kwicket.component.config.requiresSubclass

/**
 * Creates an [TextArea] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [TextArea]
 * @param id Wicket component id to use for the [TextArea]
 * @receiver configuration for creating the [TextArea]
 * @return [TextArea] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> ITextAreaConfig<T>.invoke(id: String): TextArea<T> {
    val model = model
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : TextArea<T>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        TextArea<T>(id, model)
    }.config(this)
}