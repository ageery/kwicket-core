package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.ZonedDateTimeField
import org.kwicket.component.config
import org.kwicket.component.config.IIZonedDateTimeFieldConfig
import org.kwicket.component.config.useAnonSubClass
import java.time.LocalDate
import java.time.LocalTime
import java.time.ZonedDateTime

fun zonedDateTimeFieldFactory(
    id: String,
    config: IIZonedDateTimeFieldConfig
): ZonedDateTimeField =
    if (config.useAnonSubClass()) {
        object : ZonedDateTimeField(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                config.onConfig?.invoke(this)
            }

            override fun getLocalDate(temporal: ZonedDateTime): LocalDate =
                config.toZonedDate?.invoke(temporal) ?: super.getLocalDate(temporal)

            override fun getLocalTime(temporal: ZonedDateTime): LocalTime =
                config.toZonedTime?.invoke(temporal) ?: super.getLocalTime(temporal)

            override fun getDefaultTime(): LocalTime? =
                config.defaultTime?.invoke() ?: super.getDefaultTime()

        }
    } else {
        ZonedDateTimeField(id, config.model)
    }.config(config).apply { config.postInit?.invoke(this) }