package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.Radio
import org.kwicket.component.config
import org.kwicket.component.config.IRadioConfig

//  FIXME: should radio config extend labelcomponent should there be a generic method for that?

/**
 * Creates a [Radio] component based on the configuration and with a Wicket identifier of [id].
 *
 * @param T type of the model of the [Radio]
 * @param id Wicket component id to use for the [Radio]
 * @receiver configuration for creating the [Radio]
 * @return [Radio] component based on the configuration and with a Wicket identifier of [id]
 */
operator fun <T> IRadioConfig<T>.invoke(id: String): Radio<T> =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        val model = model
        val group = group
        object : Radio<T>(id, model, group) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

        }
    } else {
        Radio(id, model, group)
    }.config(this)
        .also { radio ->
            label?.let { radio.label = it }
        }