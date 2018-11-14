package org.kwicket.component.factory

import org.apache.wicket.extensions.markup.html.form.datetime.LocalDateTimeField
import org.kwicket.component.config
import org.kwicket.component.config.IILocalDateTimeFieldConfig
import org.kwicket.component.config.useAnonSubClass
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

fun localDateTimeFieldFactory(
    id: String,
    config: IILocalDateTimeFieldConfig
): LocalDateTimeField =
    if (config.useAnonSubClass()) {
        object : LocalDateTimeField(id, config.model) {

            override fun onConfigure() {
                super.onConfigure()
                config.onConfig?.invoke(this)
            }

            override fun getLocalDate(temporal: LocalDateTime): LocalDate =
                config.toLocalDate?.invoke(temporal) ?: super.getLocalDate(temporal)

            override fun getLocalTime(temporal: LocalDateTime): LocalTime =
                config.toLocalTime?.invoke(temporal) ?: super.getLocalTime(temporal)

            override fun getDefaultTime(): LocalTime? =
                config.defaultTime?.invoke() ?: super.getDefaultTime()

        }
    } else {
        LocalDateTimeField(id, config.model)
    }.config(config).apply { config.postInit?.invoke(this) }