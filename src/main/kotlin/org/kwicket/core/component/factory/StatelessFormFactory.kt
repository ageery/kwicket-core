package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.form.StatelessForm
import org.apache.wicket.markup.html.image.Image
import org.kwicket.core.component.config
import org.kwicket.core.component.config.IStatelessFormConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates an [StatelessForm] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [StatelessForm]
 * @param id Wicket component id to use for the [StatelessForm]
 * @receiver configuration for creating the [StatelessForm]
 * @return [StatelessForm] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> IStatelessFormConfig<T>.invoke(id: String): StatelessForm<T> =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        val onSubmit = onSubmit
        val onError = onError
        val model = model
        object : StatelessForm<T>(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            override fun onSubmit() {
                onSubmit?.invoke(this)
            }

            override fun onError() {
                onError?.invoke(this)
            }

        }
    } else {
        StatelessForm(id, model)
    }.config(this)