package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.TimeField
import org.kwicket.component.config
import org.kwicket.component.config.ITimeFieldConfig

// FIXME: it feels like there should be a generic parameter...
/**
 * Creates a [TimeField] component with the Wicket identifier set to [id] and configured using [config].

 * @param id Wicket component id
 * @param config specifies the settings for the [TimeField] component
 * @return [TimeField] with the Wicket component id of [id] and configured by [config]
 */
fun timeFieldFactory(id: String, config: ITimeFieldConfig): TimeField =
    if (config.requiresSubclass) {
        val onConfig = config.onConfig
        val stateless = config.stateless
        val use12HourFormat = config.use12HourFormat
        object : TimeField(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun getStatelessHint(): Boolean = stateless ?: super.getStatelessHint()

            override fun use12HourFormat(): Boolean = use12HourFormat ?: super.use12HourFormat()

        }
    } else {
        TimeField(id, config.model)
    }.config(config)