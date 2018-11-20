package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.select.Select
import org.apache.wicket.model.IModel
import org.kwicket.component.config
import org.kwicket.component.config.ISelectConfig

fun <C: Any, T: C?> selectFactory(
    id: String,
    config: ISelectConfig<C, T>
): Select<C> {
    @Suppress("UNCHECKED_CAST")
    val model = config.model as IModel<C>
    return if (config.requiresSubclass) {

        object : Select<C>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                config.onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                config.stateless ?: super.getStatelessHint()

        }
    } else {
        Select(id, model)
    }.config(config)
}