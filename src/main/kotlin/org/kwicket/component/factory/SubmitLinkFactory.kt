package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.SubmitLink
import org.apache.wicket.markup.html.link.StatelessLink
import org.kwicket.component.config
import org.kwicket.component.config.ISubmitLinkConfig

/**
 * Creates a [SubmitLink] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [SubmitLink]
 * @param id Wicket component id
 * @param config specifies the settings for the [SubmitLink] component
 * @return [SubmitLink] with the Wicket component id of [id] and configured by [config]
 */
fun <T> submitLinkFactory(id: String, config: ISubmitLinkConfig<T>): SubmitLink =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : SubmitLink(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        SubmitLink(id, config.model)
    }.config(config)