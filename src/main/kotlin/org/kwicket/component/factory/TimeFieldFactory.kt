package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.TimeField
import org.apache.wicket.markup.html.form.TextField
import org.kwicket.component.config
import org.kwicket.component.config.ITimeFieldConfig
import org.kwicket.component.config.requiresSubclass

// FIXME: it feels like there should be a generic parameter...
/**
 * Creates a [TimeField] component with the Wicket identifier set to [id] and configured using [config].

 * @param id Wicket component id
 * @receiver specifies the settings for the [TimeField] component
 * @return [TimeField] with the Wicket component id of [id] and configured by [config]
 */
operator fun ITimeFieldConfig.invoke(id: String): TimeField =
    if (requiresSubclass) {
        val onConfig = onConfig
        val stateless = stateless
        val use12HourFormat = use12HourFormat
        val model = model
        object : TimeField(id, model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            override fun use12HourFormat(): Boolean = use12HourFormat ?: super.use12HourFormat()

        }
    } else {
        TimeField(id, model)
    }.config(this)