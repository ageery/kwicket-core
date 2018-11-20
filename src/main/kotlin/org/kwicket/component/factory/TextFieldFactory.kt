package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.TextField
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ITextFieldConfig
import org.kwicket.toJavaType

fun <C : Any, T : C?> textFieldFactory(id: String, config: ITextFieldConfig<C, T>): TextField<C> {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<C?>
    val type = config.type.toJavaType(isRequired = config.isRequired)

    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : TextField<C>(id, model, type) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()
        }
    } else {
        TextField<C>(id, model, type)
    }.config(config)
}