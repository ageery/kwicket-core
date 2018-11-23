package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.SubmitLink
import org.apache.wicket.markup.html.link.StatelessLink
import org.kwicket.component.config
import org.kwicket.component.config.ISubmitLinkConfig
import org.kwicket.component.config.requiresSubclass

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
        object : SubmitLink(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        SubmitLink(id, model)
    }.config(this)