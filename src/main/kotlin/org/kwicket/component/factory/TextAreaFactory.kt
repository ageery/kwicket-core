package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.SubmitLink
import org.apache.wicket.markup.html.form.TextArea
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ITextAreaConfig

/**
 * Creates an [TextArea] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param C type of the [TextArea]
 * @param T type of the model of the [TextArea]
 * @param id Wicket component id to use for the [TextArea]
 * @receiver configuration for creating the [TextArea]
 * @return [TextArea] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <C : Any, T : C?> ITextAreaConfig<C, T>.invoke(id: String): TextArea<C> {
    @Suppress("UNCHECKED_CAST")
    val model = model as IModel<C?>
    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : TextArea<C>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        TextArea<C>(id, model)
    }.config(this)
}