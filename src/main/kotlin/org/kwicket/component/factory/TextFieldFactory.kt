package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ITextFieldConfig
import org.kwicket.toJavaType

/**
 * Creates a [TextField] component with the Wicket identifier set to [id] and configured using [config].

 * @param C type of the [TextField]
 * @param T type of the model of the [TextField]
 * @param id Wicket component id
 * @param config specifies the settings for the [TextField] component
 * @return [TextField] with the Wicket component id of [id] and configured by [config]
 */
operator fun <C : Any, T : C?> ITextFieldConfig<C, T>.invoke(id: String): TextField<C> {
    @Suppress("UNCHECKED_CAST")
    val model = model as IModel<C?>
    val type = type.toJavaType(isRequired = isRequired)

    return if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        object : TextField<C>(id, model, type) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()
        }
    } else {
        TextField<C>(id, model, type)
    }.config(this)
}