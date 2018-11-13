package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.TimeField
import org.kwicket.component.config
import org.kwicket.component.config.ITimeFieldConfig
import org.kwicket.component.config.useAnonSubClass

fun timeFieldFactory(
    id: String,
    config: ITimeFieldConfig
): TimeField =
    if (config.useAnonSubClass()) {
        object : TimeField(id, config.model) {
            override fun onConfigure() {
                super.onConfigure()
                config.onConfig?.invoke(this)
            }

            override fun use12HourFormat(): Boolean {
                return config.use12HourFormat ?: super.use12HourFormat()
            }
        }
    } else {
        TimeField(id, config.model)
    }.config(config).apply { config.postInit?.invoke(this) }