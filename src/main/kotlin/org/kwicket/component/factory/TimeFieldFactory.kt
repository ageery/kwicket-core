package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.TimeField
import org.kwicket.component.config
import org.kwicket.component.config.ITimeFieldConfig
import org.kwicket.component.config.useAnonSubClass

fun timeFieldFactory(
    id: String,
    config: ITimeFieldConfig
): TimeField {
    val postInit = config.postInit
    return if (config.useAnonSubClass()) {
        val use12HourFormat = config.use12HourFormat
        val onConfig = config.onConfig
        object : TimeField(id, config.model) {
            override fun onConfigure() {
                super.onConfigure()
                onConfig?.invoke(this)
            }

            override fun use12HourFormat(): Boolean {
                return use12HourFormat ?: super.use12HourFormat()
            }
        }
    } else {
        TimeField(id, config.model)
    }.config(config).apply { postInit?.invoke(this) }
}