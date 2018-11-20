package org.kwicket.component.factory

import org.apache.wicket.markup.html.form.Radio
import org.kwicket.component.config
import org.kwicket.component.config.IRadioConfig

//  FIXME: should radio config extend labelcomponent should there be a generic method for that?
fun <T> radioFactory(id: String, config: IRadioConfig<T>): Radio<T> =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        object : Radio<T>(id, config.model, config.group) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean =
                stateless ?: super.getStatelessHint()

        }
    } else {
        Radio(id, config.model, config.group)
    }.config(config)
        .also { radio ->
            config.label?.let { radio.label = it }
        }