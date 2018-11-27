package org.kwicket.core.component.factory

import org.apache.wicket.markup.html.form.SubmitLink
import org.kwicket.core.component.config
import org.kwicket.core.component.config.ISubmitLinkConfig
import org.kwicket.core.component.config.requiresSubclass

/**
 * Creates an [SubmitLink] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [SubmitLink]
 * @param id Wicket component id to use for the [SubmitLink]
 * @receiver configuration for creating the [SubmitLink]
 * @return [SubmitLink] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> ISubmitLinkConfig<T>.invoke(id: String): SubmitLink =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        val model = model
        val onSubmit = onSubmit
        val onError = onError
        val form = form
        object : SubmitLink(id, model, form) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            override fun onSubmit() {
                super.onSubmit()
                onSubmit?.invoke(this)
            }

            override fun onError() {
                super.onError()
                onError?.invoke(this)
            }
        }
    } else {
        SubmitLink(id, model, form)
    }.config(this)