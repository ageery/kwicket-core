package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.TextArea
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ITextAreaConfig

fun <C : Any, T : C?> textAreaFactory(
    id: String,
    config: ITextAreaConfig<C, T>
): TextArea<C> {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<C?>
    return if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : TextArea<C>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        TextArea<C>(id, model)
    }.config(config)
}