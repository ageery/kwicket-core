package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.Radio
import org.kwicket.component.config
import org.kwicket.component.config.IRadioConfig

//  FIXME: should radio config extend labelcomponent should there be a generic method for that?

/**
 * Creates a [Radio] component with the Wicket identifier set to [id] and configured using [config].

 * @param T type of the model of the [Radio]
 * @param id Wicket component id
 * @param config specifies the settings for the [Radio] component
 * @return [Radio] with the Wicket component id of [id] and configured by [config]
 */
fun <T> radioFactory(id: String, config: IRadioConfig<T>): Radio<T> =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : Radio<T>(id, config.model, config.group) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Radio(id, config.model, config.group)
    }.config(config)
        .also { radio ->
            config.label?.let { radio.label = it }
        }